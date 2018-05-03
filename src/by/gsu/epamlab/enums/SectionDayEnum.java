package by.gsu.epamlab.enums;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import by.gsu.epamlab.beans.User;

public enum SectionDayEnum {
  TODAY {
    @Override
    public String getQuerery(User user) {
      LocalDate today = LocalDate.now();
      return "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND dateCreate = '"+ today + "' "
          + "AND recycle_Bin = 0";
    }
  },TOMORROW {
    @Override
    public String getQuerery(User user) {
      LocalDate today = LocalDate.now();
      LocalDate tomorrow = today.plus(1,ChronoUnit.DAYS);
      return  "SELECT * FROM eeproject.tasks "
      + "WHERE UserId = (SELECT UserId FROM eeproject.user "
      + "WHERE login = '" + user.getLogin().trim() + "')"
      + "AND dateModified = '"+ tomorrow + "'"
      + "AND recycle_Bin = 0";
    }
  },SOMEDAY {
    @Override
    public String getQuerery(User user) {
      LocalDate today = LocalDate.now();
      LocalDate tomorrow = today.plus(1,ChronoUnit.DAYS);
      return  "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND dateModified > '"+ tomorrow +"' "
              + " AND recycle_Bin = 0";
    }
  },FIXED {
    @Override
    public String getQuerery(User user) {
      return  "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
              + "AND report = 1 AND recycle_Bin = 0";
    }
  },RECYCLE_BIN {
    @Override
    public String getQuerery(User user) {
      return "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND recycle_Bin = 1";
    }
  };

  public static final String SECTION_PARAM = "section";
  public abstract String getQuerery(User user);
  public static SectionDayEnum getValueByParam(HttpServletRequest request){
    return SectionDayEnum.valueOf(SectionDayEnum.class, request.getParameter(SECTION_PARAM).toUpperCase());
  }

}
