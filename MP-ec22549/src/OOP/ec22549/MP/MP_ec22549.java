package OOP.ec22549.MP;

public class MP_ec22549 extends House_ec22549 {
    private String[] roomUsernames = new String[]{"ec22549", "ec22852", "ec22617", "ec22621", "ec22675", "ec22726", "ec22752", "ec22597", "ec22995"};
    public GUIVisitor_ec22549 v = new GUIVisitor_ec22549("EC22549's Dark House", roomUsernames);

    public static void main(String[] args) {
        MP_ec22549 h = new MP_ec22549();
        h.visit(h.v, Direction.FROM_SOUTH);
    }
}
