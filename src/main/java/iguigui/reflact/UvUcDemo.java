package iguigui.reflact;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UvUcDemo {


    //对字段进行分组
    //最后需要操作的key就是这些字段的排列组合
    //可以一个也不选，也可以只选一个
    static List<String[]> fieldList = List.of(
            new String[]{"dspUserId", "adGroupId", "adPlanId"},
            new String[]{"sspUserId", "adPlaceId"},
            new String[]{"adPlacePayType"},
            new String[]{"environment"});

    public static void main(String[] args) {

        //基于我们的key设计，我们可以简单的把key分为两个部分
        //date,hour：稳定的，不会变的，每个小时执行的时候是一样的
        //dspUserId,adGroupId,adPlanId,sspUserId,adPlaceId,adPlacePayType,environment：不稳定的
        //这里的核心就是如何根据我们的key设计，计算出来不稳定的部分内容
        //事实上不稳定的部分就是每一个字段的排列组合，每个字段只有两种状态，选中或者不选中
        //同时更进一步的，dspUserId,adGroupId,adPlanId这三个参数是互斥的，只能选一个
        //例如选了dspUserId，则adGroupId、adPlanId都不会出现
        //sspUserId,adPlaceId同理，也是一组
        //最后我们应该计算的就是四组数据，每一组中各选1个或者不选，一共有4*3*2*2 = 48种组合
        //List.of(
        //            new String[]{"dspUserId", "adGroupId", "adPlanId"},
        //            new String[]{"sspUserId", "adPlaceId"},
        //            new String[]{"adPlacePayType"},
        //            new String[]{"environment"});


        String uid = "123456789";//用户ID
        int type = 3; //曝光
        //对于每一条曝光 or 点击记录，我们都可以转换成一个如下的关键信息Entity
        Entity entity = new Entity("2023-02-07", 8,
                10L, 20L, 30L,
                40L, 50L,
                1,
                2);

        //接下来我们需要使用Entity中的参数组装得到我们到底需要在哪些纬度下进行累加用户id
        //比如我们需要在2023-02-07这一天，以8点为纬度，以10L,20L,30L,40L,50L这些纬度下进行累加用户id

        int loopCount = 1;
        for (String[] strings : fieldList) {
            loopCount *= (strings.length + 1);
        }
        //48次
        ArrayList<List<String>> lists = new ArrayList<>();
        //0代表不选择，无意义
        int[] index = {0, 0, 0, 0};
        for (int i = 0; i < loopCount; i++) {
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < index.length; j++) {
                if (index[j] - 1 >= 0) {
                    strings.add(fieldList.get(j)[index[j] - 1]);
                } else {
                    strings.add("-1");
                }
            }
            index[0]++;
            for (int j = 0; j < index.length - 1; j++) {
                if (index[j] > fieldList.get(j).length) {
                    index[j] = 0;
                    index[j + 1]++;
                }
            }
            lists.add(strings);
        }

        //这里我们就得到了到底要计算哪些排列组合的key
        System.out.println("需要进行排列组合的field列表");
        lists.forEach(System.out::println);

        //实体类转换成field给后文进反射
        Class<? extends Entity> entityClass = entity.getClass();
        Map<String, Field> fieldMap = new HashMap<>();
        for (String[] strings : fieldList) {
            for (String fieldName : strings) {
                try {
                    Field declaredField = entityClass.getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    fieldMap.put(fieldName, declaredField);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //前面的内容都可以缓存起来，开机执行一次即可


        //组装参数得到一大堆的key
        List<String> keys = lists.stream().map(it -> {
            ArrayList<String> keysTmp = new ArrayList<>();
            for (int i = 0; i < it.size(); i++) {
                String fieldName = it.get(i);
                String[] strings = fieldList.get(i);
                for (String string : strings) {
                    String value;
                    if (fieldName.equals(string)) {
                        try {
                            value = fieldMap.get(fieldName).get(entity).toString();
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        value = "-1";
                    }
                    keysTmp.add(value);
                }
            }
            return String.join("_", keysTmp);
        }).collect(Collectors.toList());

        System.out.println("转换成参数,得到真正的key");
        //得到你的key
        keys.forEach(System.out::println);
        //最后你在这一组key前面拼上date和hour即可得到真正的key
        //根据这个key自己构建缓存，然后进行累加用户ID即可
        //最后只需要把这个缓存遍历出来存DB
    }

}


class Entity {

    private String date;

    private Integer hour;

    private Long dspUserId;

    private Long adGroupId;

    private Long adPlanId;

    private Long sspUserId;

    private Long adPlaceId;

    private Integer adPlacePayType;

    private Integer environment;

    public Entity(String date, Integer hour, Long dspUserId, Long adGroupId, Long adPlanId, Long sspUserId, Long adPlaceId, Integer adPlacePayType, Integer environment) {
        this.date = date;
        this.hour = hour;
        this.dspUserId = dspUserId;
        this.adGroupId = adGroupId;
        this.adPlanId = adPlanId;
        this.sspUserId = sspUserId;
        this.adPlaceId = adPlaceId;
        this.adPlacePayType = adPlacePayType;
        this.environment = environment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getDspUserId() {
        return dspUserId;
    }

    public void setDspUserId(Long dspUserId) {
        this.dspUserId = dspUserId;
    }

    public Long getAdGroupId() {
        return adGroupId;
    }

    public void setAdGroupId(Long adGroupId) {
        this.adGroupId = adGroupId;
    }

    public Long getAdPlanId() {
        return adPlanId;
    }

    public void setAdPlanId(Long adPlanId) {
        this.adPlanId = adPlanId;
    }

    public Long getSspUserId() {
        return sspUserId;
    }

    public void setSspUserId(Long sspUserId) {
        this.sspUserId = sspUserId;
    }

    public Long getAdPlaceId() {
        return adPlaceId;
    }

    public void setAdPlaceId(Long adPlaceId) {
        this.adPlaceId = adPlaceId;
    }

    public Integer getAdPlacePayType() {
        return adPlacePayType;
    }

    public void setAdPlacePayType(Integer adPlacePayType) {
        this.adPlacePayType = adPlacePayType;
    }

    public Integer getEnvironment() {
        return environment;
    }

    public void setEnvironment(Integer environment) {
        this.environment = environment;
    }
}