package QueryTicket;

import com.alibaba.fastjson.JSON;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by geyalu on 2016/11/10.
 */
public class TransJson {

    public static void main(String[] args) {

        String jsonString = "{\"data\":{\"datas\":[{\"train_no\":\"490000Z27202\",\"station_train_code\":\"Z272\",\"start_station_telecode\":\"QDK\",\"zy_num\":\"--\",\"swz_num\":\"--\"}],\"flag\":true,\"searchDate\":\"2016年11月17号&nbsp;&nbsp;周四\"}}";

        transJson(jsonString);

    }


    public static ArrayList transJson(String jsonString) {

        ArrayList DataList  = new ArrayList();

        Root group2 = JSON.parseObject(jsonString, Root.class);
        //System.out.println("group2:" + group2);

        int size = group2.getData().getDatas().size();

        for (int i = 0; i < size; i++) {


            //System.out.println(group2.getData().getDatas().get(i));

            DataList.add(group2.getData().getDatas().get(i));
        }
        return DataList;

    }

}


class Datas {

    private String train_no;

    private String station_train_code;

    private String start_station_telecode;

    private String start_station_name;

    private String end_station_telecode;

    private String end_station_name;

    private String from_station_telecode;

    private String from_station_name;

    private String to_station_telecode;

    private String to_station_name;

    private String gg_num;

    private String gr_num;

    private String qt_num;

    private String rw_num;

    private String rz_num;

    private String tz_num;

    private String wz_num;

    private String yb_num;

    private String yw_num;

    private String yz_num;

    private String ze_num;

    private String zy_num;

    private String swz_num;

    private String lishi;

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getStation_train_code() {
        return station_train_code;
    }

    public void setStation_train_code(String station_train_code) {
        this.station_train_code = station_train_code;
    }

    public String getStart_station_telecode() {
        return start_station_telecode;
    }

    public void setStart_station_telecode(String start_station_telecode) {
        this.start_station_telecode = start_station_telecode;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public String getEnd_station_telecode() {
        return end_station_telecode;
    }

    public void setEnd_station_telecode(String end_station_telecode) {
        this.end_station_telecode = end_station_telecode;
    }

    public String getEnd_station_name() {
        return end_station_name;
    }

    public void setEnd_station_name(String end_station_name) {
        this.end_station_name = end_station_name;
    }

    public String getFrom_station_telecode() {
        return from_station_telecode;
    }

    public void setFrom_station_telecode(String from_station_telecode) {
        this.from_station_telecode = from_station_telecode;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getTo_station_telecode() {
        return to_station_telecode;
    }

    public void setTo_station_telecode(String to_station_telecode) {
        this.to_station_telecode = to_station_telecode;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getGg_num() {
        return gg_num;
    }

    public void setGg_num(String gg_num) {
        this.gg_num = gg_num;
    }

    public String getGr_num() {
        return gr_num;
    }

    public void setGr_num(String gr_num) {
        this.gr_num = gr_num;
    }

    public String getQt_num() {
        return qt_num;
    }

    public void setQt_num(String qt_num) {
        this.qt_num = qt_num;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getTz_num() {
        return tz_num;
    }

    public void setTz_num(String tz_num) {
        this.tz_num = tz_num;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getYb_num() {
        return yb_num;
    }

    public void setYb_num(String yb_num) {
        this.yb_num = yb_num;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getZe_num() {
        return ze_num;
    }

    public void setZe_num(String ze_num) {
        this.ze_num = ze_num;
    }

    public String getZy_num() {
        return zy_num;
    }

    public void setZy_num(String zy_num) {
        this.zy_num = zy_num;
    }

    public String getSwz_num() {
        return swz_num;
    }

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    @Override
    public String toString() {
        return "Datas{" +
                "train_no='" + train_no + '\'' +
                ", station_train_code='" + station_train_code + '\'' +
                ", start_station_telecode='" + start_station_telecode + '\'' +
                ", start_station_name='" + start_station_name + '\'' +
                ", end_station_telecode='" + end_station_telecode + '\'' +
                ", end_station_name='" + end_station_name + '\'' +
                ", from_station_telecode='" + from_station_telecode + '\'' +
                ", from_station_name='" + from_station_name + '\'' +
                ", to_station_telecode='" + to_station_telecode + '\'' +
                ", to_station_name='" + to_station_name + '\'' +
                ", gg_num='" + gg_num + '\'' +
                ", gr_num='" + gr_num + '\'' +
                ", qt_num='" + qt_num + '\'' +
                ", rw_num='" + rw_num + '\'' +
                ", rz_num='" + rz_num + '\'' +
                ", tz_num='" + tz_num + '\'' +
                ", wz_num='" + wz_num + '\'' +
                ", yb_num='" + yb_num + '\'' +
                ", yw_num='" + yw_num + '\'' +
                ", yz_num='" + yz_num + '\'' +
                ", ze_num='" + ze_num + '\'' +
                ", zy_num='" + zy_num + '\'' +
                ", swz_num='" + swz_num + '\'' +
                ", lishi='" + lishi + '\'' +
                '}';
    }
}


class Data {
    private List<Datas> datas;

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }

    public List<Datas> getDatas() {
        return this.datas;
    }

    @Override
    public String toString() {
        return "Data{" +
                "datas=" + datas +
                '}';
    }
}


class Root {
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Root{" +
                "data=" + data +
                '}';
    }
}
