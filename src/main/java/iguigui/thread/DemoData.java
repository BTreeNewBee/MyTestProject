package iguigui.thread;

public class DemoData {

    //材料编号	名称	单位	采购单价	类别	克重	包/匹	供应商编号	财务编号	财务颜色编号
    private String materialCode;
    private String materialName;
    private String unit;
    private String purchasePrice;
    private String category;
    private String weight;
    private String packageNum;
    private String supplierCode;
    private String financeCode;
    private String financeColorCode;

    //    private String materialCode;
    //    private String materialName;
    //    private String unit;
    //    private String purchasePrice;
    //    private String category;
    //    private String weight;
    //    private String packageNum;
    //    private String supplierCode;
    //    private String financeCode;
    //    private String financeColorCode;

    //create table material(
    //    id int primary key auto_increment,
    //    material_code varchar(20),
    //    material_name varchar(20),
    //    unit varchar(20),
    //    purchase_price varchar(20),
    //    category varchar(20),
    //    weight varchar(20),
    //    package_num varchar(20),
    //    supplier_code varchar(20),
    //    finance_code varchar(20),
    //    finance_color_code varchar(20)
    //);


    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getFinanceColorCode() {
        return financeColorCode;
    }

    public void setFinanceColorCode(String financeColorCode) {
        this.financeColorCode = financeColorCode;
    }

    @Override
    public String toString() {
        //insert into material (material_code,material_name,unit,purchase_price,category,weight,package_num,supplier_code,finance_code,finance_color_code) values ('1','2','3','4','5','6','7','8','9','10');
        return "insert into test01 (material_code,material_name,unit,purchase_price,category,weight,package_num,supplier_code,finance_code,finance_color_code) values ('" + materialCode + "','" + materialName + "','" + unit + "','" + purchasePrice + "','" + category + "','" + weight + "','" + packageNum + "','" + supplierCode + "','" + financeCode + "','" + financeColorCode + "')";
    }

}
