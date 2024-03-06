// Função executada quando a janela é carregada
window.onload = function() {
    // Carrega e renderiza as tarefas
    loadAndRenderTasks();

    // Mostra a tabela e oculta o formulário
    document.querySelector('.tabela').style.display = 'block';
    document.querySelector('.formulario_actions').style.display = 'none';

    // Adiciona evento de mudança ao seletor de operações
    document.getElementById('operacoes').onchange = function () {
        var campo_select = document.getElementById('operacoes');
        var valor_selecionado = campo_select.value;

        if (valor_selecionado === 'opt2') {
            document.querySelector('.tabela').style.display = 'none'; 
            document.querySelector('.formulario_actions').style.display = 'inline-flex';
        } else {
            document.querySelector('.tabela').style.display = 'block'; 
            document.querySelector('.formulario_actions').style.display = 'none'; 
        }
    };

    // Adiciona evento de clique ao botão de envio do formulário
    document.querySelector('.button_form_submit').onclick = function(event) {
        event.preventDefault();

        // Obtém os valores dos campos do formulário
        var nomeTarefa = document.getElementById('name').value;
        var dataEntrega = document.getElementById('date').value;
        var status = document.querySelector('input[name="status"]:checked').value;
        var categoria = [];
        document.querySelectorAll('input[name="categoria"]:checked').forEach(function(checkbox) {
            categoria.push(checkbox.value);
        });
        var nivelPrioridade = document.querySelector('input[name="level"]:checked').value;
        var descricao = document.getElementById('msg').value;

        // Cria um objeto representando uma nova tarefa
        var novaTarefa = {
            nomeTarefa: nomeTarefa,
            dataEntrega: dataEntrega,
            status: status,
            categoria: categoria,
            nivelPrioridade: nivelPrioridade,
            descricao: descricao
        };

        // Adiciona a nova tarefa à tabela
        addTaskToTable(novaTarefa);

        // Adiciona a nova tarefa ao array de tarefas
        storedTasks.push(novaTarefa);
        // Salva as tarefas no armazenamento local
        localStorage.setItem('tasks', JSON.stringify(storedTasks));

        // Limpa os campos do formulário
        document.getElementById('name').value = '';
        document.getElementById('date').value = '';
        document.querySelector('input[name="status"]:checked').checked = false;
        document.querySelectorAll('input[name="categoria"]:checked').forEach(function(checkbox) {
            checkbox.checked = false;
        });
        document.querySelectorAll('input[name="level"]:checked').checked = false;
        document.getElementById('msg').value = '';
    };
};
