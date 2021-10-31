package lb.edu.aub.cmps297.reserva.models;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String cellPhone;
    private int numSeats;
    private String description;
    private String location;
    private int img;
    private boolean isFav;
    private int reservationCounter;
    private Menu menu;

    public Restaurant(String name, String cellPhone, int numSeats, String description, String location, int img) {
        this.name = name;
        this.cellPhone = cellPhone;
        this.numSeats = numSeats;
        this.description = description;
        this.location = location;
        this.img = img;
        this.isFav = false;
        this.reservationCounter = 0;
    }

    public Restaurant(String name, String cellPhone, int numSeats, String description, String location, int img, boolean isFav) {
        this.name = name;
        this.cellPhone = cellPhone;
        this.numSeats = numSeats;
        this.description = description;
        this.location = location;
        this.img = img;
        this.isFav = isFav;
        this.reservationCounter = 0;
    }
    public Restaurant(String name, String cellPhone, int numSeats, String description, String location, int img, Menu menu) {
        this.name = name;
        this.cellPhone = cellPhone;
        this.numSeats = numSeats;
        this.description = description;
        this.location = location;
        this.img = img;
        this.isFav = isFav;
        this.reservationCounter = 0;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public int getReservationCounter() {
        return reservationCounter;
    }

    public void setReservationCounter(int reservationCounter) {
        this.reservationCounter = reservationCounter;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
