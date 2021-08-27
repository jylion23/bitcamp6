package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyHandler {

  List<Privacy> memberList;

  public PrivacyHandler(List<Privacy> memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[회원 등록]");

    Privacy privacy = new Privacy();

    privacy.setNumber(Prompt.inputInt("번호? "));
    privacy.setName(Prompt.inputString("이름? "));
    privacy.setNickname(Prompt.inputString("닉네임? "));
    privacy.setEmail(Prompt.inputString("이메일? "));
    privacy.setBirthday(Prompt.inputDate("생일? "));
    privacy.setPassword(Prompt.inputString("암호? "));
    privacy.setPhoto(Prompt.inputString("사진? "));
    privacy.setPhoneNumber(Prompt.inputString("전화? "));
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(privacy);
  }

  public void list() {
    System.out.println("[회원 목록]");

    Privacy[] list = memberList.toArray(new Privacy[0]);

    for (Privacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          member.getNumber(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Privacy member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("닉네임: %s\n", member.getNickname());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("생일: %s\n", member.getBirthday());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getPhoneNumber());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
  }

  public void update() {
    System.out.println("[회원 변경]");
    int no = Prompt.inputInt("번호? ");

    Privacy member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    Date birthDay = Prompt.inputDate("생일(" + member.getBirthday() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getPhoneNumber() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setNickname(nickName);
    member.setEmail(email);
    member.setBirthday(birthDay);
    member.setPassword(password);
    member.setPhoto(photo);
    member.setPhoneNumber(tel);

    System.out.println("회원을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[회원 삭제]");
    int no = Prompt.inputInt("번호? ");

    Privacy member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    }

    memberList.remove(member);

    System.out.println("회원을 삭제하였습니다.");
  }

  private Privacy findByNo(int no) {
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String promptMember(String label) {
    while (true) {
      String owner = Prompt.inputString(label);
      if (this.exist(owner)) {
        return owner;
      } else if (owner.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public String promptMembers(String label) {
    String members = "";
    while (true) {
      String member = Prompt.inputString(label);
      if (this.exist(member)) {
        if (members.length() > 0) {
          members += ",";
        }
        members += member;
        continue;
      } else if (member.length() == 0) {
        break;
      } 
      System.out.println("등록된 회원이 아닙니다.");
    }
    return members;
  }
}







