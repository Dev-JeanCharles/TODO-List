// Array para armazenar as tarefas
var storedTasks = JSON.parse(localStorage.getItem('tasks')) || [];

// Função para carregar e renderizar as tarefas
function loadAndRenderTasks() {
    // Limpa a tabela antes de renderizar as tarefas
    clearTable();

    // Renderiza as tarefas armazenadas no armazenamento local
    storedTasks.forEach(function(task) {
        addTaskToTable(task);
    });
}

// Função para limpar a tabela
function clearTable() {
    var tableBody = document.querySelector('.tabela tbody');
    tableBody.innerHTML = '';
}

// Função para adicionar uma tarefa à tabela
function addTaskToTable(task) {
    var newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${task.nomeTarefa || 'titulo da task'}</td>
        <td>${task.dataEntrega || '00 de janeiro de 2024 16:55'}</td>
        <td>
            <select class="status-select">
                <option value="todo" ${task.status === 'todo' ? 'selected' : ''}>todo</option>
                <option value="doing" ${task.status === 'doing' ? 'selected' : ''}>doing</option>
                <option value="done" ${task.status === 'done' ? 'selected' : ''}>done</option>
            </select>
        </td>
        <td>
            ${task.categoria && task.categoria.includes('categoria1') ? 'Videos' : ''}
            ${task.categoria && task.categoria.includes('categoria2') ? 'Documentação' : ''}
        </td>
        <td>${task.nivelPrioridade ? task.nivelPrioridade.charAt(task.nivelPrioridade.length - 1) : '5'}</td>
        <td>${task.descricao || 'essa é uma descrição'}</td>
        <td>
            <button class="botao_actions" onclick="editTask(this)">
                <span class="material-symbols-outlined">edit_square</span>
            </button>
            <button class="botao_actions" onclick="deleteTask(this)">
                <span class="material-symbols-outlined">delete</span>
            </button>
        </td>
    `;
    document.querySelector('.tabela tbody').appendChild(newRow);

    // Adiciona evento de mudança ao campo de seleção de status
    newRow.querySelector('.status-select').addEventListener('change', function() {
        var selectedStatus = this.value;
        var rowIndex = Array.from(this.closest('tr').parentNode.children).indexOf(this.closest('tr'));

        // Atualiza o valor na tabela
        storedTasks[rowIndex].status = selectedStatus;

        // Atualiza as informações no armazenamento local
        localStorage.setItem('tasks', JSON.stringify(storedTasks));
    });
}

// Função para deletar uma tarefa
function deleteTask(button) {
    var row = button.closest('tr');
    var index = Array.from(row.parentNode.children).indexOf(row);

    // Remove a linha da tabela
    row.remove();

    // Remove a tarefa do armazenamento local
    storedTasks.splice(index, 1);
    localStorage.setItem('tasks', JSON.stringify(storedTasks));
}

// Função para editar uma tarefa
function editTask(button) {
    var row = button.closest('tr');
    var index = Array.from(row.parentNode.children).indexOf(row);

    // Obtém os elementos da linha para edição
    var nomeField = row.cells[0];
    var dataEntregaField = row.cells[1];
    var descricaoField = row.cells[5];
    var nivelPrioridadeField = row.cells[4];

    // Cria campos de entrada para edição
    var nomeInput = createInputField(nomeField.innerText);
    var dataEntregaInput = createDateInputField(dataEntregaField.innerText); // Usando input de data
    var descricaoInput = createInputField(descricaoField.innerText);
    var nivelPrioridadeInput = createInputField(nivelPrioridadeField.innerText);

    // Substitui os elementos da linha pelos campos de entrada
    nomeField.innerHTML = '';
    nomeField.appendChild(nomeInput);

    dataEntregaField.innerHTML = '';
    dataEntregaField.appendChild(dataEntregaInput);

    descricaoField.innerHTML = '';
    descricaoField.appendChild(descricaoInput);

    nivelPrioridadeField.innerHTML = '';
    nivelPrioridadeField.appendChild(nivelPrioridadeInput);

    // Adiciona evento de tecla para salvar as edições quando pressionar Enter
    [nomeInput, descricaoInput, nivelPrioridadeInput].forEach(function(input) {
        input.addEventListener('keyup', function(event) {
            if (event.key === 'Enter') {
                // Atualiza os campos na tabela
                nomeField.innerText = nomeInput.value;
                descricaoField.innerText = descricaoInput.value;
                nivelPrioridadeField.innerText = nivelPrioridadeInput.value;

                // Atualiza os campos no array storedTasks
                storedTasks[index].nomeTarefa = nomeInput.value;
                storedTasks[index].descricao = descricaoInput.value;
                storedTasks[index].nivelPrioridade = nivelPrioridadeInput.value;

                // Atualiza as informações no armazenamento local
                localStorage.setItem('tasks', JSON.stringify(storedTasks));
            }
        });
    });

    // Adiciona evento de mudança para salvar a edição da data de entrega
    dataEntregaInput.addEventListener('change', function() {
        // Atualiza o campo na tabela
        dataEntregaField.innerText = dataEntregaInput.value;

        // Atualiza o campo no array storedTasks
        storedTasks[index].dataEntrega = dataEntregaInput.value;

        // Atualiza as informações no armazenamento local
        localStorage.setItem('tasks', JSON.stringify(storedTasks));
    });

    // Define o foco no primeiro campo de entrada
    nomeInput.focus();
}

// Função para criar um campo de entrada de texto
function createInputField(value) {
    var input = document.createElement('input');
    input.type = 'text';
    input.value = value;
    return input;
}

// Função para criar um campo de entrada de data
function createDateInputField(value) {
    var input = document.createElement('input');
    input.type = 'date';
    input.value = value;
    return input;
}
