import java.io.IOException;

/**
 * 爬虫线程
 */
public class CrawlerThread implements Runnable{
    private int start;
    private int end;

    public CrawlerThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        try {
            CrawlUtils.crawl(start,end);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
