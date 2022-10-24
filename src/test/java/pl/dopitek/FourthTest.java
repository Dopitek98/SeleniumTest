package pl.dopitek;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FourthTest {
    @Test(dependsOnMethods = {"thirdTest"})
    public void firstTest(){
        System.out.println("First test");
    }

    @Test(dependsOnMethods = {"firstTest"})
    public void secondTest(){
        System.out.println("Second test");
    }

    @Test
    public void thirdTest(){
        System.out.println("Third test");
    }

    @Test(dataProvider = "data")
    public void dpTest(String val){
        System.out.println(val);
    }

    @DataProvider(name = "data")
    public Object[][] dataProvider(){
        return new Object[][] {{"I am 1 test"},{"I am 2 test"},{"I am 3 test"}};
    }
}
