package Travel;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelService {
    Scanner sc = null;
    TravelDao dao;
    ArrayList<TravelVo> travels = null;

    TravelService(Scanner sc) {
        this.sc = sc;
        dao = TravelDao.getTravelDao();
    }

    void printAllPage(){
        travels = dao.viewAll();
        int page = 1;
        int lastPage = travels.size()%5==0 ? travels.size()/5 : travels.size()/5+1;
        int start = 0;
        int end = Math.min(5, travels.size());
        boolean flag = true;
        while(flag){
            for(int i=start;i<end;i++){
                System.out.println(travels.get(i));
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
                    end = Math.min(end + 5, travels.size());
                }
            }
            else if(c.equals("e")){
                flag = false;
            }
        }

    }

    void printArea(){
        System.out.println("어느 권역의 관광지를 보실건가요?");
        ArrayList<String> districts = dao.getDistricts();
        for(int i=0;i<districts.size();i++){
            System.out.println( i+1 +". "+ districts.get(i));
        }
        int choice = sc.nextInt();
        String[] query = {districts.get(choice-1)};
        travels = dao.searchbyDistrict(query);
        for(TravelVo travel : travels) {
            System.out.println(travel);
            System.out.println("=================================================");
        }

    }

    void printSearchResult() {
        System.out.println("\n=== 검색기준 ===");
        System.out.println("1. 권역");
        System.out.println("2. 주소");
        System.out.println("3. 관광지명");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt(); // 사용자로부터 메뉴 선택 입력 받음
        System.out.println("검색할 검색어를 입력해주세요, 공백으로 글자들을 구분하고 입력하신 검색어 중 하나라도 포함되어있는 결과가 출력됩니다");
        sc.nextLine();
        String search = sc.nextLine();
        String[] str = search.split(" ");

        switch (choice) { // 선택한 옵션에 따라 동작 분기
            case 1:
                travels = dao.searchbyDistrict(str);
                break;
            case 2:
                travels = dao.searchbyAddress(str);
                break;
            case 3:
                travels = dao.searchbyTitle(str);
                break;
            default:
                System.out.println("Invalid choice. Try again."); // 잘못된 입력 메시지 출력

        }

        for(TravelVo travel : travels){
            System.out.println(travel);
            System.out.println("=================================================");
        }
    }

}
