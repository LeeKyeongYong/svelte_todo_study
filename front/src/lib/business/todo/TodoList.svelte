<script lang="ts">
	import type { Todo } from './TodoReq.svelte';
	import TodoEdit from './TodoEdit.svelte';

	const { todos } = $props<{ todos: Todo[] }>();
	let todoEdit = $state() as any;

	const completed = $derived(todos.filter((todo) => todo.completed).length);
	$effect(() => {
		for (const todo of todos) console.log(todo.content, todo.completed);
	});
</script>

<h2>할일 목록({completed}/{todos.length})</h2>
<ul class="grid gap-2">
	{#each todos as todo (todo.id)}
		<li class="flex items-center gap-2">
			{todo.content}
			<input type="checkbox" class="checkbox" bind:checked={todo.completed} />
			<button class="btn btn-outline btn-sm" onclick={() => todoEdit.showTodoEditModal(todo)}
				>수정</button
			>
			<button class="btn btn-outline btn-sm">삭제</button>
		</li>
	{/each}
</ul>

<TodoEdit bind:this={todoEdit} {todos} />