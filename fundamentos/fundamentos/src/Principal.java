public class Principal {

    public static void main(String[] args) {
        
        Pessoa p1 = new Pessoa();

        p1.setNome("Maria");
        // p1.nome = "Joao";
        // p1.email = "joao@gmail.com";

        System.out.println("Nome: "+p1);
        // System.out.println("Email: "+p1.email);

        Pessoa p2 = new Pessoa();

        p2.setNome("Junior");

        // p2.nome = "Júnior";
        // p2.email = "junior@gmail";

        Aluno a1 = new Aluno();
        a1.setNome("Mateus");
        Aluno a2 = new Aluno();
        a2.setNome("Gleice");
        a2.setEmail("gleice@sou.ufac.br");
        a2.setMatricula(1);
        System.out.println("Nome: "+a2.getNome()+" Mat: "+a2.getMatricula());
        System.out.println("Email: "+a2.getEmail());

        //Polimorfismo
        Pessoa a3 = new Aluno();
        a3.setNome("Polimorfismo");
        
    }
    
}
