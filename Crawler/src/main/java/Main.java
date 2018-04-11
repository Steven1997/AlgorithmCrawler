import java.io.*;
import java.util.*;


public class Main {
    private static List<String> candidate = new ArrayList<String>(); //存放候选的算法分类
    private static Category[] categories = new Category[50];//保存不同分类，包括名称和出现次数

    static {  //导入候选的算法分类
        InputStream is = null;
        try {
            is = new FileInputStream("./resource/algorithm.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is,"GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                candidate.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {
        //开8个线程爬取
        Thread th[] = new Thread[8];
        for(int i = 0;i < 8;i++){
            th[i] = new Thread(new CrawlerThread((i + 1) * 1000,(i + 1) * 1000 + 50));
            th[i].start();
        }
    }

    public static List<String> getCandidate() {
        return candidate;
    }

    public static void setCandidate(List<String> candidate) {
        Main.candidate = candidate;
    }

    public static Category[] getCategories() {
        return categories;
    }

    public static void setCategories(Category[] categories) {
        Main.categories = categories;
    }
}
