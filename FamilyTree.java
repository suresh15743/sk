import java.util.Scanner;
class FamilyMember {
    String name;
    FamilyMember left, right;
    public FamilyMember(String name) {
        this.name = name;
        left = right = null;
    }
}
class FamilyTree {
    FamilyMember root;
    void inorder(FamilyMember member) {
        if (member != null) {
            inorder(member.left);
            System.out.println(" " + member.name);
            inorder(member.right);
        }
    }
    void preorder(FamilyMember member) {
        if (member != null) {
            System.out.println(" " + member.name);
            preorder(member.left);
            preorder(member.right);
        }
    }
    void postorder(FamilyMember member) {
        if (member != null) {
            postorder(member.left);
            postorder(member.right);
            System.out.println(" " + member.name);
        }
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        FamilyTree family = new FamilyTree();
        System.out.print("Enter Grandfather's name: ");
        String grandfatherName = scanner.nextLine();
        family.root = new FamilyMember(grandfatherName);
        System.out.print("Enter Grandmother's name: ");
        String grandmotherName = scanner.nextLine();
        family.root.right = new FamilyMember(grandmotherName);
        System.out.print("Enter Father's name: ");
        String fatherName = scanner.nextLine();
        family.root.left = new FamilyMember(fatherName);
        System.out.print("Enter Mother's name: ");
        String motherName = scanner.nextLine();
        family.root.right.left = new FamilyMember(motherName);
        System.out.print("Enter ElderSon's name: ");
        String EldersonName = scanner.nextLine();
        family.root.left.left = new FamilyMember(EldersonName);
        System.out.print("Enter YoungerSon'sname: ");
        String YoungersonName = scanner.nextLine();
        family.root.left.right = new FamilyMember(YoungersonName);
        System.out.println("\nInorder Traversal:");
        family.inorder(family.root);
        System.out.println("\nPreorder Traversal:");
        family.preorder(family.root);
        System.out.println("\nPostorder Traversal:");
        family.postorder(family.root);
        scanner.close();
    }
}