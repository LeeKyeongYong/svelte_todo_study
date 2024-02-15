<script lang="ts">
	import type { Todo } from './TodoReq.svelte';
	import { makeTodo } from './TodoReq.svelte';

	const { todos } = $props<{ todos: Todo[] }>();

	function addTodo(this: HTMLFormElement) {
		const form = this;

		if (form.todoContent.value.trim().length === 0) return;

		todos.push(makeTodo(todos.length + 1, form.todoContent.value, false));
		form.todoContent.value = '';
		form.todoContent.focus();
	}
</script>

<h2>할일 추가</h2>
<form on:submit|preventDefault={addTodo}>
	<input name="todoContent" type="text" class="input input-bordered" placeholder="할일" />
	<input type="submit" value="추가" class="btn btn-primary" />
</form>