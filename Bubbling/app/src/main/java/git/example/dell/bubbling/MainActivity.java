package git.example.dell.bubbling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //绑定控件，省去写findviewbyid的重复性操作
    @BindView(R.id.tv_MAX)
    TextView tv_MAX;
    @BindView(R.id.tv_MIN)
    TextView tv_MIN;
    @BindView(R.id.tv_CONTENT)
    TextView tv_CONTENT;

    int[] arr={44,33,11,22,55,77,66,88};
    String CONTENT="一、基本思想：" +"\n"+
            "两个数比较大小，较小的数下沉，较大的数冒起来" +"\n\n"+
            "二、过程：" +"\n"+
            "1、比较相邻的两个数据，如果第二个数小，就交换位置。" +"\n"+
            "2、从后向前两两比较，一直比较最前两个数据。最终最小数被交换到起始的位置，这样第一个最小数的位置就排好了。" +"\n"+
            "3、继续重复上述过程，依次将第2.3...n-1个最小数拍好位置" +"\n\n"+
            "三、平均时间复杂度：O(n2)" +"\n\n"+
            "四、优化：" +"\n"+
            "1、针对问题：数据的顺序排好之后，冒泡算法仍然会继续进行下一轮的比较，直到arr.length-1次，后面的比较没有意义的。" +"\n"+
            "2、方案：设置标志位flag，如果发生了交换flag设置为true；如果没有交换就设置为false。" +
            "这样当一轮比较结束后如果flag仍为false，即：这一轮没有发生交换，说明数据的顺序已" +
            "经排好，没有必要继续进行下去。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        //冒泡详细介绍
        tv_CONTENT.setText(CONTENT);

        MAX_MIN(arr);
        MIN_MAX(arr);
    }
    //大到小  未优化
    private void MAX_MIN(int[] arr) {
        int temp;//临时变量
        for (int i = 0; i < arr.length - 1; i++) {//表示趟数，一共arr.length-1次。

            for (int j =arr.length-1; j >i ; j--) {
                //从大到小
                if (arr[j]>arr[j-1]){
                    temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        String con;
        for(int i=0;i<arr.length;i++){
            System.out.println("大到小"+arr[i]);
            con=arr[i]+" ";
            StringBuilder append = builder.append(con);
            tv_MAX.setText("从大到小:"+append.toString());
        }

    }



    //小到大  已优化
    private void MIN_MAX(int[] arr) {
        int temp;//临时变量
        boolean flag;//是否交换的标志
        for (int i = 0; i < arr.length - 1; i++) {//表示趟数，一共arr.length-1次。

            flag = false;
            for (int j =arr.length-1; j >i ; j--) {

                if (arr[j]<arr[j-1]){//从小大到大
                    temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;

                    flag = true;
                }
            }

            if(!flag) break;
        }

        StringBuilder builder = new StringBuilder();
        String con;
        for(int i=0;i<arr.length;i++){
            System.out.println("小到大"+arr[i]);
            con=arr[i]+" ";
            StringBuilder append = builder.append(con);
            tv_MIN.setText("从小到大:"+append.toString());
        }

    }



}

