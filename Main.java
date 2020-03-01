import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Map;

class FriendList
{
        //instantiate GetPath class        
        GetPath gP = new GetPath(); 

        private final String friend;
        private final String path;

        // Friends attributes
        private String nickname;
        private String fullname;
        private String ip;
        private String img;

        public static TreeMap<String, FriendList> friendMap = new TreeMap<>();

        public FriendList(final String friend) {
            this.friend = friend;
            this.path = gP.getPath(this.friend).next(); // init path of friend when object created
            // gP.getPath((this.friend)).close();
        }

        // Getter and Setters
        public String getFriend() {
            return friend;
        }

        public String returnPath() {
            return path;
        }

        public void setFriendNickname(final String n) {
            nickname = n;
        }

        public String getFriendNickname() {
            return nickname;
        }

        public void setFriendFullName(final String fname) {
            fullname = fname;
        }

        public String getFriendFullName() {
            return fullname;
        }

        public void setFriendIp(final String i) {
            ip = i;
        }

        public String getFriendIP() {
            return ip;
        }

        public void setFriendIMG(final String im) {
            img = im;
        }

        public String getFriendIMG() {
            return img;
        }

    }

    class Logs {
        GetPath gP = new GetPath();

        private final String log1;
        private final String lPath;

        private String nickname;
        private String fullname;
        private String ip;
        private String img;
        private String message;

        public static ArrayList<String> arr = new ArrayList<>();

        public Logs(final String logName) {
            this.log1 = logName;
            this.lPath = gP.getPath(this.log1).next();
            // gP.getPath(this.log).close();
        }

        // Getter and Setters
        public String getLog() {
            return log1;
        }

        public String returnLogPath() {
            return lPath;
        }

        public void setLogNickname(final String n) {
            nickname = n;
        }

        public String returnLogNickname() {
            return nickname;
        }

        public void setLogFullname(final String fname) {
            fullname = fname;
        }

        public String getLogFullname() {
            return fullname;
        }

        public void setLogIP(final String i) {
            ip = i;
        }

        public String getLogIP() {
            return ip;
        }

        public void setLogIMG(final String image) {
            img = image;
        }

        public String getLogIMG() {
            return img;
        }

        public void setMessage(final String msg) {
            message = msg;
        }

        public String getMessage() {
            return message;
        }

    }

    class Reader {
        public TreeMap<String, FriendList> ReadFriends() {
            try {
                final FriendList friend = new FriendList("friends.list");
                final Scanner sc = new Scanner(new File(friend.returnPath()));

                while (sc.hasNext()) {
                    final FriendList fL = new FriendList("friends.list");
                    fL.setFriendNickname(sc.nextLine());
                    fL.setFriendFullName(sc.nextLine());
                    fL.setFriendIp(sc.nextLine());
                    fL.setFriendIMG(sc.nextLine());

                    fL.setFriendNickname(fL.getFriendNickname().replaceAll("[<>]", "").replaceAll("\\[.*?]", ""));
                    fL.setFriendFullName(fL.getFriendFullName().replaceAll("\\[.*?]", ""));

                    fL.setFriendIp(fL.getFriendIP().replaceAll("\\[.*?]", ""));
                    fL.setFriendIMG(fL.getFriendIMG().replaceAll("\\[.*?]", ""));

                    FriendList.friendMap.put(fL.getFriendNickname(), fL);
                }

                sc.close();
            } catch (final IOException e) {
                System.out.println(e);
            }

            return FriendList.friendMap;

        }

        public ArrayList<String> ReadPubChat() throws NullPointerException {
            try {
                final Logs log = new Logs("Eurakarte.log");
                final Scanner sc = new Scanner(new File(log.returnLogPath()));

                while (sc.hasNext()) {
                    Logs lObj = new Logs("Eurakarte.log");
                    lObj.setLogNickname(sc.nextLine());

                    lObj.setLogNickname(lObj.returnLogNickname().replaceAll("\\[.*?]", "").trim());
                    final int indx = lObj.returnLogNickname().indexOf(">");

                    final String msg = lObj.returnLogNickname().substring(indx + 1, lObj.returnLogNickname().length());
                    final String nicks = lObj.returnLogNickname().replaceAll("<", "").replaceAll(">", "").substring(0,
                            indx - 1);

                    lObj.setMessage(lObj.returnLogNickname().substring(indx + 1, lObj.returnLogNickname().length()));
                    lObj.setLogNickname(
                            lObj.returnLogNickname().replaceAll("<", "").replaceAll(">", "").substring(0, indx - 1));

                    Logs.arr.add(nicks + ": " + msg.trim());

                }
                sc.close();

            } catch (final IOException e) {
                System.out.println(e);
            }

            return Logs.arr;

        }

        public ArrayList<String> ReadPrivChat(final String find[]) {
            final Logs log = new Logs("Donut[AFK].log");
            final ArrayList<String> arList = new ArrayList<>();
            final ArrayList<String> nicknames = new ArrayList<>();

            try {
                final Scanner sc = new Scanner(new File(log.returnLogPath()));
                while (sc.hasNext()) {
                    final Logs lObj = new Logs("Donut[AFK].log");
                    lObj.setLogNickname(sc.nextLine());

                    lObj.setLogNickname(lObj.returnLogNickname().replaceAll("\\[.*?]", "").trim());
                    final int indx = lObj.returnLogNickname().indexOf(">");

                    final String msg = lObj.returnLogNickname().substring(indx + 1, lObj.returnLogNickname().length());
                    final String nicks = lObj.returnLogNickname().replaceAll("<", "").replaceAll(">", "").substring(0,
                            indx - 1);

                    lObj.setMessage(msg);
                    lObj.setLogNickname(nicks);

                    arList.add(nicks + ": " + msg);
                    nicknames.add(nicks);
                }

                for (int i = 0; i < nicknames.size(); i++) {
                    final String n = nicknames.get(i);
                    if (find[1].equals(n))
                        return arList;

                    else if (!find[1].equals(n))
                        System.out.println("Not found");

                }

                sc.close();

            } catch (final IOException e) {
                System.out.println(e);
            }

            return arList;
        }

        public void Commandline(final String opt[]) {
            final int argLen = opt.length;

            if (argLen == 0) {
                System.out.println('\n' + "none: Prints a help message describing the parameters");
                System.out.println("-pf: Prints a list of the friends nicknames and real names ordered by nicknames.");
                System.out.println(
                        "-qf nickname: Prints all the information related to the given nickname. If nickname not found the text \"Not found.\" is given.");
                System.out.println(
                        "-ql nickname: Displays the private chat log related to the given nickname. If nickname not found the text \"Not found.\" is given.");
                System.out.println(
                        "-qpl: Displays the public chat log. If not found the text \"Not found\" is given." + '\n');
            }

            else if ((opt[0].equals("-qf") && argLen == 2) || opt[0].equals("-pf")) {
                boolean flag = true;

                for (final Map.Entry<String, FriendList> e : ReadFriends().entrySet()) {
                    if (opt[0].equals("-pf")) {
                        System.out.println("Nickname: " + e.getKey());
                        System.out.println("Fullname: " + e.getValue().getFriendFullName());
                    }

                    else if (opt[0].equals("-qf") && argLen == 2 && (opt[1].equals(e.getKey()))) {

                        System.out.println("Nickname: " + e.getKey());
                        System.out.println("Fullname: " + e.getValue().getFriendFullName());
                        System.out.println("IP: " + e.getValue().getFriendIP());
                        System.out.println("IMG: " + e.getValue().getFriendIMG());

                        return;
                    } else
                        flag = false;

                }
                if (!flag)
                    System.out.println("Not found.");
            }

            else if (opt[0].equals("-ql") && argLen == 2) {
                for (int i = 0; i < ReadPrivChat(opt).size(); i++)
                    System.out.println(ReadPrivChat(opt).get(i));
            }

            else if (opt[0].equals("-qpl") && !ReadPubChat().isEmpty()) {

                for (int i = 0; i < Logs.arr.size(); i++)
                    System.out.println(Logs.arr.get(i));
            }

            else if (opt[0].equals("-qpl") && Logs.arr.isEmpty())
                System.out.println("Not found.");

        }

    }

    public class Main {
        public static void main(final String args[]) {
            final Reader r = new Reader();
        r.Commandline(args);
    }

}
