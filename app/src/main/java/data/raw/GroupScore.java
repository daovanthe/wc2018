package data.raw;

import java.util.List;

public class GroupScore {
    String nameGroup;
    List<CountryScore> countryScoreList;


    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public List<CountryScore> getCountryScoreList() {
        return countryScoreList;
    }

    public void setCountryScoreList(List<CountryScore> countryScoreList) {
        this.countryScoreList = countryScoreList;
    }

    public GroupScore(String nameGroup, List<CountryScore> countryScoreList) {

        this.nameGroup = nameGroup;
        this.countryScoreList = countryScoreList;
    }
}
