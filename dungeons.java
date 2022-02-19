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
//HP、ATK、EXP
  static int mhp = 3;
  static int mhp1 = mhp;
  static int mhp2 = mhp;
  static int mhp3 = mhp;
  static int matk = 2;
  static int mexp = 1;
  static String mm = "○";
  static int mclear = 0;


  public static void main(String[] args){

    put("dungeonsへようこそ！");
    put("あなたの名前を教えてください");
    Scanner sc = new Scanner(System.in);
    name = sc.next();

    put("あなたはダンジョンの探索者です" );
    put("あなたは今日も、ダンジョンの攻略を目指し突き進みます");

    putStatus();

    putCommand();
  }
  //ステータス表示
  public static void putStatus(){
    put("-----");
    put(name+"　Lv"+lv+"　HP"+hp+"/"+(15+3*lv)+"　ATK"+atk+"　肉 "+food+"個");
    put("内部データ　exp" + exp + "　floor" + floor + "　depth"+depth);
    put("-----");
  }
  //コマンド表示
  public static void putCommand(){
    if(clear == 1){
      putGameclear();
    }else if(hp == 0){
      putGameover();
    }else{
      if(depth == 5){
      depth = 0;
      put("下へと続く階段をみつけた");
      floor += 1;
      }
      put("--地下" + floor + "階--");
      put("どうする？");
      put("1.平坦な道へ進む");
      put("2.険しい道へ進む");
      put("3.休息をとる");
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
          put("強そうだ！");
          //put("魔物のHPは" +mhp1+" "+mhp2+" "+mhp3);
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
        put("入力が正しくありません");
        putCommand();
      }
    }
  }
  //ゲームクリア表示
  public static void putGameclear(){
    put(name +"はダンジョンを攻略に成功した！");
    put("おめでとうございます！");
  }
  //ゲームオーバー表示
  public static void putGameover(){
    put(name + "は力尽きた");
    put("GAMEOVER");
  }
  //回復処理
  public static void eat(){
    if(food >= 1){
      put("魔物の肉を食べ、体をやすめた");
      food -= 1;
      int nowhp = hp;
      hp = hp + lv + 5;
      if(hp > (15 + 3 * lv)){
        hp = 15 + 3 * lv;
      }
      put("HPが" + (hp - nowhp) +"回復した");
    }else{
      put("腹が減ってあまり休めなかった");
    }
  }
  //魔物出現処理
  public static void putMonster(){
    MonsterLevel();
    Random rnd = new Random();
    int MonsterNumber = rnd.nextInt(3) + 1;
    if(floor == 10 || floor == 20){
      MonsterNumber = 1;
    }
    put("魔物が" + MonsterNumber + "体あらわれた！");
    if(MonsterNumber == 1){
      mhp2 = 0;
      mhp3 = 0;
    }else if(MonsterNumber == 2){
      mhp3 = 0;
    }
    //put("魔物のHPは" +mhp1+" "+mhp2+" "+mhp3);
  }
  //魔物レベル処理
  public static void MonsterLevel(){
    if(floor < 10){
      mhp = 3 * floor;
      matk = 1 + (int)(floor / 2 );
      mexp = 1;
      mm = "○";
    }else if(floor == 10){
      mhp = 50;
      matk = 7;
      mexp = 10;
      mm ="（－_－）";
    }else if(floor > 10 && floor < 20){
      mhp = 4 * floor;
      matk = 4 + (int)(floor / 2 );
      mexp = 2;
      mm = "●";
    }else if(floor == 20){
      mhp = 150;
      matk = 10;
      mexp = 100;
      mm ="（○＿○）";
      mclear = 1;
    }else if(floor > 20){
      mhp = 5 * floor;
      matk = 7 + (int)(floor / 2 +1);
      mexp = 3;
      mm = "★";
    }
    mhp1 = mhp;
    mhp2 = mhp;
    mhp3 = mhp;
  }
  //戦闘処理
  public static void putFight(){
    if(hp != 0){
      if(mhp2 > 0){
        System.out.print(mm);
      }
      if(mhp3 > 0){
        System.out.print(mm);
      }
      put(mm);
      put("どうする？");
      put("1.攻撃する");
      put("2.全力で攻撃する");
      put("3.回避に徹する");
      Scanner sc = new Scanner(System.in);
      try{
        int c =sc.nextInt();
        switch(c){
          case 1:
            put(name + "の攻撃！");
            put("魔物に" + atk + "のダメージ！");
            if(mhp3 != 0){
              mhp3 = mhp3 - atk;
              if(mhp3 <= 0){
                mhp3 = 0;
                put("魔物を一体たおした！");
                e += mexp;
              }
            }else if(mhp2 != 0){
              mhp2 = mhp2 - atk;
              if(mhp2 <= 0){
                mhp2 = 0;
                put("魔物を一体たおした！");
                e += mexp;
              }
            }else if(mhp1 != 0){
              mhp1 = mhp1 - atk;
              if(mhp1 <= 0){
                mhp1 = 0;
                put("魔物を一体たおした！");
                e += mexp;
                clear = mclear;
              }
            }
            putMonsterTurn();
            break;
          case 2:
            put(name +"の全力の攻撃！");
            put("魔物に" + 2 * atk + "のダメージ！");
            if(mhp3 != 0){
              mhp3 = mhp3 - 2 * atk;
              if(mhp3 <= 0){
                mhp3 = 0;
                put("魔物を一体たおした！");
                e += mexp;
                clear = mclear;
              }
            }else if(mhp2 != 0){
              mhp2 = mhp2 - 2 * atk;
              if(mhp2 <= 0){
                mhp2 = 0;
                put("魔物を一体たおした！");
                e += mexp;
              }
            }else if(mhp1 != 0){
              mhp1 = mhp1 - 2 * atk;
              if(mhp1 <= 0){
                mhp1 = 0;
                put("魔物を一体たおした！");
                e += mexp;
              }
            }
            put("体力を消耗し、HPを" + 3 +"失った！");
            hp -= 3;
            if(hp < 0){
              hp = 0;
            }
            putMonsterTurn();
            break;
          case 3:
            put("魔物からの攻撃を避けつつ、息を整えた");
            int nowhp = hp;
            hp = hp + lv;
            if(hp > (15 + 3 * lv)){
              hp = 15 + 3 * lv;
            }
            put("HPが" + (hp - nowhp) +"回復した");
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
        put("入力が正しくありません");
        putFight();
      }
    }
  }
  //魔物の攻撃処理
  public static void putMonsterTurn(){
    if(mhp1 > 0 && hp!= 0){
      put("--");
      put("魔物の攻撃！");
      put(name + "に" + matk + "のダメージ！");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
    if(mhp2 > 0 && hp!= 0){
      put("--");
      put("魔物の攻撃！");
      put(name + "に" + matk + "のダメージ！");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
    if(mhp3 > 0 && hp!= 0){
      put("--");
      put("魔物の攻撃！");
      put(name + "に" + matk + "のダメージ！");
      hp -= matk;
      if(hp < 0){
        hp = 0;
      }
    }
  }
  //魔物の肉獲得の処理
  public static void putMeet(){
    if(hp != 0 ){
      put("魔物を解体した");
      Random rnd = new Random();
      int Meet = rnd.nextInt(3);
      if(Meet == 1){
        food += 1;
        put("うまく解体できた！");
      }else{
        put("うまくいかなかった");
      }
    }
  }
  //レベルアップ処理
  public static void LvUp(){
    put("経験値を"+ e + "得た！");
    exp = exp + e;
    e = 0;
    for(int i = exp;i >= lv;exp = exp - lv +1){
    i = exp - lv;
    lv += 1;
    hp += 3;
      put("Lvがあがった！");
    }
    atk = (int)lv / 2 + 2;
  }
  //入力の例外処理
  public static void putEx(){
    put("入力が正しくありません");
  }

  //文字列表示
  public static void put( String str){
    System.out.println(str);
  }
}
