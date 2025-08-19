package Travel;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelService {
    Scanner sc = null;
    TravelDao dao;
    ArrayList<TravelVo> travels = null;

    TravelService(Scanner sc) {
        this.sc = sc;
        dao =  new TravelDao("jdbc:mysql://localhost:3306/skel","root","1234");
    }

    void printAllPage(){
        travels = dao.executeQuery("select * from travel where no between 1 and 20");
        int page = 1;
        int lastPage = travels.size()%5==0 ? travels.size()/5 : travels.size()/5+1;
        int start = 0;
        int end = 5<travels.size() ? 5:travels.size();
        boolean flag = true;
        while(flag){
            for(int i=start;i<end;i++){
                System.out.println(travels.get(i).getNo());
                System.out.println("권역 : "+travels.get(i).getDistrict());
                System.out.println("위치 : " + travels.get(i).getAddress());
                System.out.println("명칭 : " + travels.get(i).getTitle());
                System.out.println("상세정보 : " + travels.get(i).getDescription());
                System.out.println("연락처 : " + travels.get(i).getAddress());
                System.out.println("=================================================");
            }
            if(page==1){
                System.out.print("              ");
            }else{
                System.out.print("<<이전 페이지(q입력)");
            }
            System.out.print("  page("+page+"/"+ lastPage+")  ");
            if(page==lastPage){
                System.out.print("              ");
            }else{
                System.out.print("다음 페이지(w입력)>>");
            }
            System.out.print("그만 보기는 e");
            String c = sc.nextLine();
            if(c.equals("q")){
                if(page>1){
                    page--;
                    start = start-5;
                    end = (end-5)%5==0 ? end-5: 5*(end/5);
                }
            }else if(c.equals("w")){
                if(page<lastPage){
                    page++;
                    start = start+5;
                    end = end + 5 <=travels.size() ? end + 5 :travels.size();
                }
            }
            else if(c.equals("e")){
                flag = false;
            }
        }



    }

    void printArea(){

    }

    void printSearchResult(){

    }

}
