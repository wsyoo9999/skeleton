package Travel;

public class TravelVo {
    private final int no;
    private final String district;
    private final String title;
    private final String description;
    private final String address;
    private final String phone;

    TravelVo(int no, String district, String title, String description, String address, String phone) {
        this.no = no;
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }
    public int getNo() {
        return no;
    }
    public String getDistrict() {
        return district;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        String str = this.getNo()+"\n"+
                    "권역 : "+this.getDistrict()+
                    "위치 : " + this.getAddress()+
                    "명칭 : " + this.getTitle()+
                    "상세정보 : " + this.getDescription()+
                    "연락처 : " + this.getAddress();
        return str;
    }
}
