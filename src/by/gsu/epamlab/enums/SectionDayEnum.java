package by.gsu.epamlab.enums;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import by.gsu.epamlab.beans.User;


public enum SectionDayEnum {
  TODAY {
    @Override
    public String getQuerery(User user) {
      return "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND dateCreate = '"+ LocalDate.now() + "'";
    }
  },TOMORROW {
    @Override
    public String getQuerery(User user) {
      LocalDate today = LocalDate.now();
      LocalDate tomorrow = today.plus(1,ChronoUnit.DAYS);
      return  "SELECT * FROM eeproject.tasks "
      + "WHERE UserId = (SELECT UserId FROM eeproject.user "
      + "WHERE login = '" + user.getLogin().trim() + "')"
      + "AND dateCreate = '"+ tomorrow + "'";
    }
  },SOMEDAY {
    @Override
    public String getQuerery(User user) {
      return  "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND dateModified ";
    }
  },FIXED {
    @Override
    public String getQuerery(User user) {
      return  "SELECT * FROM eeproject.tasks "
          + "WHERE UserId = (SELECT UserId FROM eeproject.user "
          + "WHERE login = '" + user.getLogin().trim() + "')"
          + "AND report = 1";
    }
  },RECYCLE_BIN {
    @Override
    public String getQuerery(User user) {
      // TODO Auto-generated method stub
      return null;
    }
  };

  public static final String SECTION_PARAM = "section";
  public abstract String getQuerery(User user);
  public static SectionDayEnum getValueByParam(HttpServletRequest request){
    return SectionDayEnum.valueOf(SectionDayEnum.class, request.getParameter(SECTION_PARAM).toUpperCase());
  }

}
