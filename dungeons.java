import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class dungeons{
  static String name;
  static int lv = 1;
  static int hp = 15 + lv * 3;
  static int atk = (int)lv / 2 + 2;
  static int food = 1;

  static int exp = 0;
  static int e = 0;
  static int floor = 1;
  static int depth = 0;
  static int clear = 0;
//HP�AATK�AEXP
  static int mhp = 3;
  static int mhp1 = mhp;
  static int mhp2 = mhp;
  static int mhp3 = mhp;
  static int matk = 2;
  static int mexp = 1;
  static String mm = "��";
  static int mclear = 0;


  public static void main(String[] args){

    put("dungeons�ւ悤�����I");
    put("���Ȃ��̖��O�������Ă�������");
    Scanner sc = new Scanner(System.in);
    name = sc.next();

    put("���Ȃ��̓_���W�����̒T���҂ł�" );
    put("���Ȃ��͍������A�_���W�����̍U����ڎw���˂��i�݂܂�");

    putStatus();

    putCommand();
  }
  //�X�e�[�^�X�\��
  public static void putStatus(){
    put("-----");
    put(name+"�@Lv"+lv+"�@HP"+hp+"/"+(15+3*lv)+"�@ATK"+atk+"�@�� "+food+"��");
    put("�����f�[�^�@exp" + exp + "�@floor" + floor + "�@depth"+depth);
    put("-----");
  }
  //�R�}���h�\��
  public static void putCommand(){
    if(clear == 1){
      putGameclear();
    }else if(hp == 0){
      putGameover();
    }else{
      if(depth == 5){
      depth = 0;
      put("���ւƑ����K�i���݂���");
      floor += 1;
      }
      put("--�n��" + floor + "�K--");
      put("�ǂ�����H");
      put("1.���R�ȓ��֐i��");
      put("2.���������֐i��");
      put("3.�x�����Ƃ�");
      Scanner sc = new Scanner(System.in);

      try{
        int c =sc.nextInt();
        if(c == 1){
          putMonster();
          putFight();
          putMeet();
          depth += 1;
          putStatus();
          putCommand();
        }else if(c == 2){
          floor += 10;
          putMonster();
          put("���������I");
          //put("������HP��" +mhp1+" "+mhp2+" "+mhp3);
          putFight();
          if(hp <= 0){
            hp = 0;
          }
          putMeet();
          floor -= 10;
          depth += 2;
          if(depth > 5){
            depth = 5;
          }
          putStatus();
          putCommand();
        }else if(c == 3){
          eat();
          putStatus();
          putCommand();
        }else if(c == 4){
          System.exit(0);
        }else{
          putEx();
          putCommand();
        }
      } catch(InputMismatchException e){
        put("���͂�����������܂���");
        putCommand();
      }
    }
  }
  //�Q�[���N���A�\��
  public static void putGameclear(){
    put(name +"�̓_���W�������U���ɐ��������I");
    put("���߂łƂ��������܂��I");
  }
  //�Q�[���I�[�o�[�\��
  public static void putGameover(){
    put(name + "�͗͐s����");
    put("GAMEOVER");
  }
  //�񕜏���
  public static void eat(){
    if(food >= 1){
      put("�����̓���H�ׁA�̂��₷�߂�");
      food -= 1;
      int nowhp = hp;
      hp = hp + lv + 5;
      if(hp > (15 + 3 * lv)){
        hp = 15 + 3 * lv;
      }
      put("HP��" + (hp - nowhp) +"�񕜂���");
    }else{
      put("���������Ă��܂�x�߂Ȃ�����");
    }
  }
  //�����o������
  public static void putMonster(){
    MonsterLevel();
    Random rnd = new Random();
    int MonsterNumber = rnd.nextInt(3) + 1;
    if(floor == 10 || floor == 20){
      MonsterNumber = 1;
    }
    put("������" + MonsterNumber + "�̂����ꂽ�I");
    if(MonsterNumber == 1){
      mhp2 = 0;
      mhp3 = 0;
    }else if(MonsterNumber == 2){
      mhp3 = 0;
    }
    //put("������HP��" +mhp1+" "+mhp2+" "+mhp3);
  }
  //�������x������
  public static void MonsterLevel(){
    if(floor < 10){
      mhp = 3 * floor;
      matk = 1 + (int)(floor / 2 );
      mexp = 1;
      mm = "��";
    }else if(floor == 10){
      mhp = 50;
      matk = 7;
      mexp = 10;
      mm ="�i�|_�|�j";
    }else if(floor > 10 && floor < 20){
      mhp = 4 * floor;
      matk = 4 + (int)(floor / 2 );
      mexp = 2;
      mm = "��";
    }else if(floor == 20){
      mhp = 150;
      matk = 10;
      mexp = 100;
      mm ="�i���Q���j";
      mclear = 1;
    }else if(floor > 20){
      mhp = 5 * floor;
      matk = 7 + (int)(floor / 2 +1);
      mexp = 3;
      mm = "��";
    }
    mhp1 = mhp;
    mhp2 = mhp;
    mhp3 = mhp;
  }
  //�퓬����
  public static void putFight(){
    if(hp != 0){
      if(mhp2 > 0){
        System.out.print(mm);
      }
      if(mhp3 > 0){
        System.out.print(mm);
      }
      put(mm);
      put("�ǂ�����H");
      put("1.�U������");
      put("2.�S�͂ōU������");
      put("3.����ɓO����");
      Scanner sc = new Scanner(System.in);
      try{
        int c =sc.nextInt();
        switch(c){
          case 1:
            put(name + "�̍U���I");
            put("������" + atk + "�̃_���[�W�I");
            if(mhp3 != 0){
              mhp3 = mhp3 - atk;
              if(mhp3 <= 0){
                mhp3 = 0;
                put("��������̂��������I");
                e += mexp;
              }
            }else if(mhp2 != 0){
              mhp2 = mhp2 - atk;
              if(mhp2 <= 0){
                mhp2 = 0;
                put("��������̂��������I");
                e += mexp;
              }
            }else if(mhp1 != 0){
              mhp1 = mhp1 - atk;
              if(mhp1 <= 0){
                mhp1 = 0;
                put("��������̂��������I");
                e += mexp;
                clear = mclear;
              }
            }
            putMonsterTurn();
            break;
          case 2:
            put(name +"�̑S�͂̍U���I");
            put("������" + 2 * atk + "�̃_���[�W�I");
            if(mhp3 != 0){
              mhp3 = mhp3 - 2 * atk;
              if(mhp3 <= 0){
                mhp3 = 0;
                put("��������̂��������I");
                e += mexp;
                clear = mclear;
              }
            }else if(mhp2 != 0){
              mhp2 = mhp2 - 2 * atk;
              if(mhp2 <= 0){
                mhp2 = 0;
                put("��������̂��������I");
                e += mexp;
              }
            }else if(mhp1 != 0){
              mhp1 = mhp1 - 2 * atk;
              if(mhp1 <= 0){
                mhp1 = 0;
                put("��������̂��������I");
                e += mexp;
              }
            }
            put("�̗͂����Ղ��AHP��" + 3 +"�������I");
            hp -= 3;
            if(hp < 0){
              hp = 0;
            }
            putMonsterTurn();
            break;
          case 3:
            put("��������̍U��������A���𐮂���");
            int nowhp = hp;
            hp = hp + lv;
            if(hp > (15 + 3 * lv)){
              hp = 15 + 3 * lv;
            }
            put("HP��" + (hp - nowhp) +"�񕜂���");
            break;
          case 4:
            System.exit(0);
          default:
            putEx();
            putFight();
        }
        if(hp != 0){
          if(mhp1 == 0 && mhp2 == 0 && mhp3 == 0){
            LvUp();
          }else{
            putStatus();
            putFight();
          }
        }
      } catch(InputMismatchException e){
        put("���͂�����������܂���");
        putFight();
      }
    }
  }
  //�����̍U������
  public static void putMonsterTurn(){
    if(mhp1 > 0 && hp!= 0){
      put("--");
      put("�����̍U���I");
      put(name + "��" + matk + "�̃_���[�W�I");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
    if(mhp2 > 0 && hp!= 0){
      put("--");
      put("�����̍U���I");
      put(name + "��" + matk + "�̃_���[�W�I");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
    if(mhp3 > 0 && hp!= 0){
      put("--");
      put("�����̍U���I");
      put(name + "��" + matk + "�̃_���[�W�I");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
  }
  //�����̓��l���̏���
  public static void putMeet(){
    if(hp != 0 ){
      put("��������̂���");
      Random rnd = new Random();
      int Meet = rnd.nextInt(3);
      if(Meet == 1){
        food += 1;
        put("���܂���̂ł����I");
      }else{
        put("���܂������Ȃ�����");
      }
    }
  }
  //���x���A�b�v����
  public static void LvUp(){
    put("�o���l��"+ e + "�����I");
    exp = exp + e;
    e = 0;
    for(int i = exp;i >= lv;exp = exp - lv +1){
    i = exp - lv;
    lv += 1;
    hp += 3;
      put("Lv�����������I");
    }
    atk = (int)lv / 2 + 2;
  }
  //���̗͂�O����
  public static void putEx(){
    put("���͂�����������܂���");
  }

  //������\��
  public static void put( String str){
    System.out.println(str);
  }
}
