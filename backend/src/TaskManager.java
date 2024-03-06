import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Stream;

public class TaskManager {

    static List<Task> taskList = new ArrayList<>();

    public static void Operations() throws InterruptedException {
        Operations operations = new Operations();
        operations.crudOperation();
        Thread.sleep(1000);
    }

    public static void getData() throws InterruptedException {
        Operations operations = new Operations();
        operations.getOperation();
        getAllTask();
    }

    public static void insertData() throws InterruptedException {
        UUID idTask = UUID.randomUUID();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira um nome para a tarefa: ");
        String name = scanner.nextLine();
        Thread.sleep(1000);

        System.out.println("Insira uma descricão: ");
        String description = scanner.nextLine();
        Thread.sleep(1000);

        System.out.println("Insira uma data de término da tarefa no formato (dd/MM/yyyy): ");

        boolean validDate = false;
        LocalDate finalDate = null;

        while (!validDate) {
            String dataFinalStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Thread.sleep(1000);
            try {
                finalDate = LocalDate.parse(dataFinalStr, formatter);
                validDate = true;
            }catch (DateTimeParseException e) {
                System.out.println("A data inserida é incorreta, tente novamente inserindo uma data válida no formato dd/MM/yyyy:");
                Thread.sleep(1000);
            }
        }
        int levelPriority;
        do {
            System.out.println("Insira um nível de prioridade (1-5)");
            levelPriority = scanner.nextInt();
            if (levelPriority < 1 || levelPriority > 5) {
                System.out.println("Nível de prioridade errado, tente novamente...");
            }
        } while (levelPriority < 1 || levelPriority > 5);
        Thread.sleep(1000);
        scanner.nextLine();

        Category category = null;
        do {
            System.out.println("Insira a categoria (video ou documentacao)");
            String categoryStr = scanner.nextLine().toUpperCase();
            try {
                category = Category.valueOf(categoryStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida. Tente novamente.");
            }
        } while (category == null);
        Thread.sleep(1000);

        Status status = null;
        do {
            System.out.println("Insira um status (todo, doing ou done)");
            String statusStr = scanner.nextLine().toUpperCase();
            try {
                status = Status.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Tente novamente.");
            }
        } while (status == null);
        Thread.sleep(1000);

        Task task = new Task(idTask, name, description, finalDate, levelPriority, category, status);

        int insertIndex = Collections.binarySearch(taskList, task,
                Comparator.comparingInt(Task::getLevelPriority).reversed());

        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }

        taskList.add(insertIndex, task);

        System.out.println(taskList);

        System.out.println("Tarefa criada com sucesso! \n");
    }

    public static void getAllTask() {
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public static void getFindCategory() {
        String categoryInput;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Insira qual categoria deseja buscar todas as tarefas (Videos ou Documentação): ");
            categoryInput = scanner.nextLine().trim();

            if (!categoryInput.equalsIgnoreCase("Videos") && !categoryInput.equalsIgnoreCase("Documentação")) {
                System.out.println("A categoria inserida foi: " + categoryInput + " é invalida, insira a categoria correta...");
            }
        } while (!categoryInput.equalsIgnoreCase("Videos") && !categoryInput.equalsIgnoreCase("Documentação"));

        final String CategoryInput = categoryInput;
        Stream<Task> taskStream = taskList.stream().filter(task -> task.getCategory().getValor().equalsIgnoreCase(CategoryInput));
        taskStream.forEach(System.out::println);
    }

    public static void getFindPriority() {
        int priorityInput;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Insira qual ordem de prioridade deseja buscar todas as tarefas (1-5): ");
            priorityInput = scanner.nextInt();

            if (priorityInput < 1 || priorityInput > 5) {
                System.out.println("A prioridade inserida foi: " + priorityInput + " é invalida, insira a prioridade correta...");
            }
        } while (priorityInput < 1 || priorityInput > 5);

        final int PriorityInput = priorityInput;
        Stream<Task> taskStream = taskList.stream().filter(task -> task.getLevelPriority() == PriorityInput);
        taskStream.forEach(System.out::println);
    }

    public static void getFindStatus() {
        String statusInput;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Insira por qual status deseja buscar todas as tarefas (Todo, Doing ou Done): ");
            statusInput = scanner.nextLine().trim();

            if (!statusInput.equalsIgnoreCase("Todo") && !statusInput.equalsIgnoreCase("Doing") && !statusInput.equalsIgnoreCase("Done")) {
                System.out.println("O status inserido foi: " + statusInput + " é invalido, insira o status correto...");
            }
        } while (!statusInput.equalsIgnoreCase("Todo") && !statusInput.equalsIgnoreCase("Doing") && !statusInput.equalsIgnoreCase("Done"));

        final String StatusInput = statusInput;
        Stream<Task> taskStream = taskList.stream().filter(task -> task.getStatus().getValor().equalsIgnoreCase(StatusInput));
        taskStream.forEach(System.out::println);
    }

    public static void deleteTask() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira qual tarefa deseja excluir por ID: ");
        String deleteTask = scanner.nextLine().trim();
        boolean found = false;
        for (Task task : taskList) {
            if (task.getIdTask().toString().equals(deleteTask)) {
                taskList.remove(task);
                found = true;
                Thread.sleep(1000);
                System.out.println("Tarefa: " + task.getName() + ", foi excluída com sucesso!");
                break;
            }
        }
        if (!found) {
            System.out.println("Não existe nenhuma tarefa com este id: " + deleteTask + ", por favor, insira um id correto...");
        }
    }
}