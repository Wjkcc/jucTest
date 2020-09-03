package javas.main.company.design_mode.singleton;

public enum M4 {
    INSTANCE;

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(M4.INSTANCE.hashCode());
            }).start();

        }
        M4 m4 = INSTANCE;
        String s = m4.getClass().getName().toString();
        System.out.println(s);
    }
}
