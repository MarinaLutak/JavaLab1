import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class task {
    public static void main (String[] args) throws Exception {
        Scanner var = new Scanner (System.in);
        System.out.println("Введіть шлях до вхідного файлу");
        String path1 = var.nextLine();
        File file1= new File(path1);
        while(!file1.isFile()){
            System.out.println("Не можливо відкрити файл, спробуйте ще раз");
            path1 = var.nextLine();
            file1= new File(path1);
        }
        System.out.println("Введіть шлях до вихідного файлу");
        String path2 = var.nextLine();
        File file2 = new File(path2);
        while(!file1.isFile()){
            System.out.println("Не можливо відкрити файл, спробуйте ще раз");
            path2 = var.nextLine();
            file2 = new File(path2);
        }
        System.out.println("Введіть роздільник");
        String delimiter0 = var.nextLine();
        while(delimiter0.length()>1){
            System.out.println("Введіть лише один символ");
            delimiter0 = var.nextLine();
        }
        char[] helper;
        helper = delimiter0.toCharArray();
        char delimiter = helper[0];
        System.out.println("Введіть символ, яким потрібно об’єднати результат");
        String unite = var.nextLine();

        Scanner scan = new Scanner(file1);
        String list;
        char[] result = {'0'};
        PrintWriter file3 = new PrintWriter(file2);
        while (scan.hasNextLine()) {
            list = scan.nextLine();
            result = list.toCharArray();

            int counter = 0;
            for (int i=0; i<result.length; i++){
                if(result[i]==delimiter && i!=result.length-1){
                    file3.write(counter + unite);
                    counter = 0;
                }
                else if(result[0]=='\"' && i==0){
                    int a = i+1;
                    while((result[a]!='\"' && (result[a+1]!=delimiter || a==result.length-1)) || (result[a]=='\"' && a!=result.length-1 && result[a+1]!=delimiter) || (result[a]!='\"' && result[a+1]==delimiter)){
                        if(a==result.length-1)
                            break;
                        counter++;
                        a++;
                    }
                    if(result[a]=='\"' && a==result.length-1){
                        file3.write(counter + "\r\n");
                    }
                    i=a;
                }
                else if(i!=0 && result[i]=='\"' && result[i-1]==delimiter){
                    if(result[i-1]==delimiter && result[i]=='\"'){
                        int a = i+1;
                        while(a!=result.length-1 && ((result[a]!='\"' && (a==result.length-1 || result[a+1]!=delimiter)) || (result[a]=='\"' && a!=result.length-1 && result[a+1]!=delimiter) || (result[a]!='\"' && result[a+1]==delimiter))){
                            if(a==result.length-1)
                                break;
                            counter++;
                            a++;
                        }
                        i=a;
                        if((result[a]=='\"' && a==result.length-1) || (result[a]=='\"' && a==result.length-2 && result[a+1]==delimiter)){
                            i++;
                            file3.write(counter + "\r\n");
                        }
                        if(result[a]!='\"' && a==result.length-1){
                            counter+=2;
                            file3.write(counter + "\r\n");
                        }
                    }
                    else{
                        counter++;
                    }
                }
                else if(i==result.length-1){
                    if(result[i]!=delimiter)
                        counter++;
                    file3.write(counter + "\r\n");
                    counter = 0;
                }
                else{
                    counter++;
                }
            }
        }
        file3.close();
    }
}