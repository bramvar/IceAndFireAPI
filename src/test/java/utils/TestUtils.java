package utils;

import com.bvar.GOTproject.model.House;
import com.bvar.GOTproject.model.Lord;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    public static final String API_ROUTE = "https://www.test.com/api/houses";

    public static House getHouse1(){
        House house = new House();
        house.setUrl("https://www.test.com/api/houses/1");
        house.setName("House 1");
        house.setRegion("region 1");
        house.setCurrentLordUrl("");
        house.setLord(null);

        return house;
    }

    public static House getHouse2(){
        House house = new House();
        house.setUrl("https://www.test.com/api/houses/2");
        house.setName("House 2");
        house.setRegion("region 2");
        house.setCurrentLordUrl("https://www.test.com/api/characters/10");
        house.setLord(null);

        return house;
    }

    public static Lord getLord1(){
        Lord lord = new Lord();
        lord.setUrl("https://www.test.com/api/characters/10");
        lord.setName("lord 1");
        lord.setGender("gender 1");

        return lord;
    }


    public static String getHousesRawJson(){
        String housesRaw = "[{\"url\":\"https://www.test.com/api/houses/1\", " +
                "\"name\":\"House 1\"," +
                "\"region\":\"region 1\"," +
                "\"currentLord\":\"\"},{" +
                "\"url\":\"https://www.test.com/api/houses/2\"," +
                "\"name\":\"House 2\"," +
                "\"region\":\"region 2\"," +
                "\"currentLord\":\"https://www.test.com/api/characters/10\"}]";

        return housesRaw;
    }

    public static String getLord1Json(){
        String lord = "{\"url\":\"https://www.test.com/api/characters/10\", " +
                "\"name\":\"lord 1\"," +
                "\"gender\":\"gender 1\"}";

        return lord;
    }
}
