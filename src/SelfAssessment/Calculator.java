package SelfAssessment;

public class Calculator {
     static int result=0;
    public void assessQues1(boolean a[]){
        int ct1=0;
        for(int i=0;i<5;i++){
            if(a[i]==true){
                ct1++;
            }
        }
        if (a[5]==true){
            ct1 = 0;
        }
        result=result+ct1;
        System.out.println(result);
    }

    public void assessQues3(boolean b){
        int ct3=0;
        if(b==true)
            ct3++;
        result=result+ct3;
        System.out.println(result);
    }

    public int assessQues4and5(boolean e[]){
        int ct4=0,ct5= 0;
        if(e[0]== true)
            ct4=ct4+2;
        if (e[1] == true)
            ct4++;
        if(e[2]==true)
            ct5++;
        result=result+ct4+ct5;
        System.out.println(result);
        return result;
    }

}
