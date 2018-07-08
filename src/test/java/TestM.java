import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestM {

    @Test
    public void Test() {
        File f = null;
        File f1 = null;
        String v;
        boolean bool = false;

        try {
            // create new file
            f = new File("C:\\a/b/c/d/test.txt");

            // returns abstract parent pathname
            f1 = f.getParentFile();
            System.out.print(f1.getParentFile().getParentFile());


            // absolute path from abstract pathname
            v = f1.getAbsolutePath();

            // true if the file path exists
            bool = f.exists();

            // if file exists
            if (bool) {
                // prints
                System.out.print("Parent file path: " + v);
            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }

    @Test
    public void tae(){
        File ad=new File("C:\\a.log");
        File ff=new File("D:\\a.log");;
        try {
            copyFileUsingApacheCommonsIO(ad,ff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void  copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    @Test
    public void tae2(){
        File[] systemRoot = getSystemRoot();
        for (int i=0;i<systemRoot.length;i++){
            System.out.println(systemRoot[i].getAbsolutePath());
        }

    }

    /**
     * 获取系统盘符
     * @return
     */
    public static File[] getSystemRoot(){
        File[] roots = File.listRoots();
        return roots;
    }
}
