import cn.eddy.utils.fileUtils.FileUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2018/7/30.
 */
public class AppMain {
    public static void main(String[] args){
        try {
            /*byte[] bytes = FileUtil.getContent("C:\\Users\\Administrator\\Desktop\\txt.txt");
            System.out.println(new String(bytes,"gbk"));*/

            List list = new ArrayList<String>();
            for (int i=0;i<9999;i++){
                Random random = new Random();
                int raint = random.nextInt();
                list.add(raint+"");
            }
            int excelThreadNum = 20;
            int asd = 0;
            for(int s=1;s<=excelThreadNum;s++){
                int count;
                int start;
                int size = list.size();
                if (s == excelThreadNum) {
                    start = (s - 1) * (size / excelThreadNum);
                    count = s * (size / excelThreadNum )+ (size % excelThreadNum);
                } else {
                    start = (s - 1) * (size / excelThreadNum);
                    count = s * (size / excelThreadNum);
                }
                List<LinkedHashMap<String,String>> sub = list.subList(start,count);
                System.err.println(sub.size());
                asd+=sub.size();
            }
            System.err.println(asd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成指定数量的线程，都放入future数组
     *
     * @param threadNum
     * @param fList
     */
    public void generate(int threadNum, List<Future<String>> fList) {
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Future<String> f = service.submit(getJob(i));
            fList.add(f);
        }
        service.shutdown();
    }

    /**
     * other things
     */
    public void doOtherThings() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("do thing no:" + i);
                Thread.sleep(1000 * (new Random().nextInt(10)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从future中获取线程结果，打印结果
     *
     * @param fList
     */
    public void getResult(List<Future<String>> fList) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(fList));
        service.shutdown();
    }

    /**
     * 生成指定序号的线程对象
     *
     * @param i
     * @return
     */
    public Callable<String> getJob(final int i) {
        final int time = new Random().nextInt(10);
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * time);
                return "thread-" + i;
            }
        };
    }

    /**
     * 生成结果收集线程对象
     *
     * @param fList
     * @return
     */
    public Runnable getCollectJob(final List<Future<String>> fList) {
        return new Runnable() {
            public void run() {
                for (Future<String> future : fList) {
                    try {
                        while (true) {
                            if (future.isDone() && !future.isCancelled()) {
                                System.out.println("Future:" + future
                                        + ",Result:" + future.get());
                                break;
                            } else {
                                Thread.sleep(1000);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
