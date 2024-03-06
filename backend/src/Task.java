import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private UUID idTask;

     String name;
     String description;
     LocalDate finalDate ;
     int levelPriority;
     Category category;
     Status status;


    public Task(UUID idTask, String name, String description, LocalDate finalDate,
                Integer levelPriority,Category category, Status status) {
        this.idTask = idTask;
        this.name = name;
        this.description = description;
        this.finalDate = finalDate;
        this.levelPriority = levelPriority;
        this.category = category;
        this.status = status;
    }

    public UUID getIdTask() {
        return idTask;
    }

    public String getName() {
        return name;
    }

    public int getLevelPriority() {
        return levelPriority;
    }

    public Category getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task" +
                "\n Id: " + idTask +
                "\n Nome: " + name +
                "\n Descrição: " + description +
                "\n Data final: " + finalDate +
                "\n Nível de prioridade: " + levelPriority +
                "\n Categoria: " + category +
                "\n Status: " + status;
    }
}
