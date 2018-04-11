import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 爬虫工具类
 */
public class CrawlUtils {
    public static void crawl(int begin,int end) throws IOException {
        //用百度搜索关键词HDU+题号+CSDN博客
        for (int pid = begin; pid <= end; pid++) {
            Document doc = Jsoup.connect("https://www.baidu.com/s?ie=utf-8&wd=HDU"
                    + pid + "CSDN博客").get();
            Elements elements = doc.select("h3.t");
            synchronized(CrawlUtils.class){
                System.out.println("HDU" + pid + "分析结果：");
                //选取百度的前5个链接，分别解析
                for (int k = 0;k < 5;k++) {
                    //对于每个链接页面，爬取article标签中的文本
                    System.out.println("第" + (k + 1) + "个链接的结果：");
                    String link = elements.get(k).selectFirst("a").attr("href");
                    String text = Jsoup.connect(link).get().select("article").text();
                    List<String> arrayList = Main.getCandidate();
                    Category[] c = Main.getCategories();
                    for (int i = 0;i < c.length;i++) {
                        c[i] = new Category();
                        c[i].setIndex(i);
                        c[i].setOccurCount(0);
                    }
                    //将文本和预先定义的算法集合进行匹配，统计不同算法出现次数
                    for (int i = 0;i < arrayList.size();i++) {
                        int start = 0;
                        while(text.indexOf(arrayList.get(i),start) != -1){
                            c[i].setOccurCount(c[i].getOccurCount() + 1);
                            start = text.indexOf(arrayList.get(i),start) + 1;
                        }
                    }
                    //按照出现次数从高到低排序
                    Arrays.sort(c, new Comparator<Category>() {
                        public int compare(Category o1, Category o2) {
                            return o2.getOccurCount() - o1.getOccurCount();
                        }
                    });
                    //输出出现次数大于1的算法标签
                    for(int i = 0;i < 50;i++){
                        if(c[i].getOccurCount() > 0){
                            System.out.println(c[i]);
                        }
                    }
            }
            System.out.println();
            }

        }
    }
}
