import java.util.Scanner;

public class Operations {

    public void crudOperation() throws InterruptedException {
        int operation;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Quais operações você deseja utilizar?");
            System.out.println("[1] Criar nova tarefa");
            System.out.println("[2] Buscar uma nova tarefa");
            System.out.println("[3] Deletar uma tarefa");
            System.out.println("[4] Voltar");
            String line = scanner.nextLine();
            operation = Integer.parseInt(line);

            switch (operation) {
                case 1:
                    TaskManager.insertData();
                    break;
                case 2:
                    TaskManager.getData();
                    break;
                case 3:
                    TaskManager.deleteTask();
                case 4:
                    break;
            }
        } while (operation != 4);
    }

    public void getOperation() throws InterruptedException {
        int operation;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Quais operações você deseja utilizar?");
            System.out.println("[1] Buscar todas as tarefas");
            System.out.println("[2] Buscar tarefas por categoria");
            System.out.println("[3] Buscar tarefas por prioridade");
            System.out.println("[4] Buscar tarefas por status");
            System.out.println("[5] Voltar para o menu principal");
            String line = scanner.nextLine();
            operation = Integer.parseInt(line);

            switch (operation) {
                case 1:
                    TaskManager.getAllTask();
                    break;
                case 2:
                    TaskManager.getFindCategory();
                    break;
                case 3:
                    TaskManager.getFindPriority();
                    break;
                case 4:
                    TaskManager.getFindStatus();
                    break;
                case 5:
                    break;
            }
        } while(operation !=5);
    }
}
