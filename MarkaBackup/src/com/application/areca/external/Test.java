package com.application.areca.external;

public class Test {
public static void main(String[] args) {
	String[] array=new String[4];
	array[0] = "-source=D:/backup_deneme/yedek/5880811898a0a18122469f577ec517b12248d23d3";
	array[1] = "-algorithm=AES_HASH";
	array[2] = "-password=ramazanfirin";
	array[3] = "-destination=c:/areca_yedek/2";
	CmdLineDeCipher.main(array);
}
}
