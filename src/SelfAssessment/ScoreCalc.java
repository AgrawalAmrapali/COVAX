package SelfAssessment;

public class ScoreCalc {
    static int result=0;
    public void assessQues1(boolean a[]){
        int ct1=0;
        for(int i=0;i<5;i++){
            if(a[i]==true){
                ct1=ct1+10;
            }
        }
        if (a[5]==true){
            ct1 = 0;
        }
        result=result+ct1;
        System.out.println(result);
    }

    public void assessQues2and3(boolean b,boolean arr[]){
        int ct3=0,ct2=0;
        for(int i=0;i<5;i++){
            if(arr[i]==true){
                ct2=ct2+2;
            }
        }
        if (arr[5]==true){
            ct2 = 0;
        }
        if(b==true)
            ct3=ct3+20;
        result=result+ct3+ct2;
        System.out.println(result);
    }

    public long assessQues4and5(boolean[] e){
        int ct4=0,ct5= 0;
        if(e[0]== true)
            ct4=ct4+100;
        if(e[1] == true)
            ct4=ct4+100;
        if(e[2]==true)
            ct5=ct5+100;
        result=result+ct4+ct5;
        System.out.println(result);
        long percent= (long) ((long) (result*100)/280.0);
        return percent;
    }
}
