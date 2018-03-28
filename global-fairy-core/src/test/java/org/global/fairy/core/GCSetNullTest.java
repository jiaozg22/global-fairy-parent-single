package org.global.fairy.core;

public class GCSetNullTest {
	public static void main(String[] args) {
		{
			byte[] placeholder = new byte[64 * 1024 * 1024];//64m空间
		}
		//加入这一行，才会回收64m内存。因为局部变量表的变量槽有复用的特性
		int i = 0;//不加这行的话，GC Root一部分的变量表仍存存有对placeholder的关联
		System.gc();
	}
}
