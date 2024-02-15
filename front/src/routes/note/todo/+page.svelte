<script lang="ts">

	interface Todo{
		id:number;
		content: string;
		completed: boolean;
	}

	function makeTodo(id: number, content: string, completed: boolean) {
		return { id, content, completed };
	}
	const todos: Todo[] = $state([
		makeTodo(1, '할일 1', false),
		makeTodo(2, '할일 2', false),
		makeTodo(3, '할일 3', false)
	]);
	const completed = $derived(todos.filter((todo) => todo.completed).length);
	$effect(() => {
		for (const todo of todos) console.log(todo.content, todo.completed);
	});
	function addTodo(this: HTMLFormElement, event: Event) {
		const form = this;
		if (form.todoContent.value.trim().length === 0) return;
		todos.push(makeTodo(todos.length + 1, form.todoContent.value, false));
		form.todoContent.value = '';
		form.todoContent.focus();
	}
</script>

{#snippet TodoList(todos)}

<h2>할일 목록</h2>
<ul>
	{#each todos as todo (todo.id)}
		<li>
			{todo.content}
			<input type="checkbox" class="checkbox" bind:checked={todo.completed} />
		</li>
	{/each}
</ul>

<hr />

<h2>할일 목록</h2>
<ul>
	{#each todos as todo (todo.id)}
		<li>
			{todo.content}
			<input type="checkbox" class="checkbox" bind:checked={todo.completed} />
		</li>
	{/each}
</ul>
{/snippet}

{@render TodoList(todos)}
<h3/>
{@render TodoList(todos)}