package APi_Negative.Accounting;

import Utils.TestDataExtractor;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class SG_NG_Users_APIs {
    String workbook = new File("D:\\hp\\untitled\\src\\main\\resources\\Users_APIs_Excel.xlsx").getAbsolutePath();

    @DataProvider(name = "Get_Users_list")
    public Object[][] Get_Users_list(){
        return TestDataExtractor.ExcelData(workbook, "Get_Users_list");
    }
    @Test(dataProvider ="Get_Users_list")
    @Story("Get_Users_list")
    public void GetUsersList(String score){
        String url ="https://devoutshade.sacredgroves.earth/api/users/level?score="+score;

    }
}
