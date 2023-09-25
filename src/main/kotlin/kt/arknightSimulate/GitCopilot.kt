package kt.arknightSimulate

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.GitAPIException
import org.eclipse.jgit.diff.DiffEntry
import org.eclipse.jgit.diff.DiffFormatter
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk
import org.eclipse.jgit.treewalk.TreeWalk
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat

fun main() {
    val repoPath = "C:\\git\\maa-copilot-store"

    val repository = Git.open(File(repoPath)).repository
    val git = Git(repository)
    val newFilesByDay = getNewFilesByDay(git)

    newFilesByDay["2023-08-20"]?.forEach { println(it) }
}

fun getNewFilesByDay(git: Git): Map<String, String> {
    val newFilesByDay = mutableMapOf<String,String>()

    val commits = git.log().all().call()
    commits.forEach { commit ->
        val commitDate = commit.authorIdent.`when`
        val dateKey = SimpleDateFormat("yyyy-MM-dd").format(commitDate)

        val parent = commit.parentCount.takeIf { it > 0 }?.let {
            RevWalk(git.repository).parseCommit(commit.getParent(0).id)
        }

        val treeWalk = TreeWalk(git.repository)
        treeWalk.isRecursive = true
        parent?.let { treeWalk.addTree(it.tree) }
        treeWalk.addTree(commit.tree)

        val diffFormatter = DiffFormatter(ByteArrayOutputStream())
        diffFormatter.setRepository(git.repository)
        val diffs = diffFormatter.scan(parent?.tree, commit.tree)
        for (diff in diffs) {
            if (diff.changeType == DiffEntry.ChangeType.ADD) {
                val newPath = diff.newPath
                //取出文件名
                val fileName = newPath.substringAfterLast("/")
                newFilesByDay[fileName] = dateKey
            }
        }

    }

    return newFilesByDay
}

