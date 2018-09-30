package android.coolweather.com.coolweather.RecyclerView;

public class Bean {

    private String name;
    private int imageId;

    public Bean(String name, int imageId) {
        this.imageId=imageId;
        this.name=name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getImageId() {

        return imageId;
    }

    public String getName() {

        return name;
    }
}
