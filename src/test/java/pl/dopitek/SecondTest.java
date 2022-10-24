package pl.dopitek;

import org.testng.annotations.Test;

public class SecondTest extends BaseTest{

    @Test(priority = 2)
    public void firstTest(){
        System.out.println("First test");
    }

    @Test(priority = 0)
    public void SecondTest(){
        System.out.println("Second test");
    }

    @Test(priority = 1)
    public void ThirdTest(){
        System.out.println("Third test");
    }
}
