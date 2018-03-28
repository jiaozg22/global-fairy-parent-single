package org.global.fairy.core;

public class FinallyTest {
	
	public static int staticfanhuiceshi(){
		int i = 0;
		try{
			i++;
			System.out.println("try");
			System.out.println("i="+i);
			return i;
		}catch(Exception e){
			e.printStackTrace();
			return i;
		}finally{
			System.out.println("进入finally");
			++i;
			System.out.println("i="+i);
			return i;
		}
	}
	
	public int fanhuiceshi(){
		int i = 0;
		int j = 0;
		try{
			i++;
			System.out.println("try");
			System.out.println("i="+i);
			j=i;
			return j;
		}catch(Exception e){
			e.printStackTrace();
			return i;
		}finally{
			System.out.println("进入finally");
			++i;
			System.out.println("i="+i);
		}
	}
	
	public static void main(String[] args) {
		FinallyTest FinallyTest = new FinallyTest();
		System.out.println("返回值："+FinallyTest.fanhuiceshi());
	}

}
