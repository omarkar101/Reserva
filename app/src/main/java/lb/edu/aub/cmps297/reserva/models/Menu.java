package lb.edu.aub.cmps297.reserva.models;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Integer> imgList;

    public Menu(ArrayList<Integer> imgList) {
        this.imgList = imgList;
    }

    public ArrayList<Integer> getImgList() {
        return imgList;
    }

    public void setImgList(ArrayList<Integer> imgList) {
        this.imgList = imgList;
    }
}
