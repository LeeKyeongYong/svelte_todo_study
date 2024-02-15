<script lang="ts">
	import type { Todo } from './TodoRq.svelte';
	import { makeTodo } from './TodoRq.svelte';
	import TodoAdd from './TodoAdd.svelte';
	import TodoList from './TodoList.svelte';
	const todos: Todo[] = $state([
		makeTodo(1, '할일 1', false),
		makeTodo(2, '할일 2', false),
		makeTodo(3, '할일 3', false)
	]);
</script>

<h1>할일</h1>

<TodoAdd {todos} />
<TodoList {todos} />