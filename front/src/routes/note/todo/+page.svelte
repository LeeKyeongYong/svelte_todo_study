<script lang="ts">
	import TodoAddAndList from '$lib/business/todo/TodoAddAndList.svelte';
</script>

<TodoAddAndList />
{#snippet TodoList(todos)}

<h2>할일</h2>
<ul>
	{#each todos as todo (todo.id)}
		<li>
			{todo.content}
			<input type="checkbox" class="checkbox" bind:checked={todo.completed} />
			<button class="btn btn-outline btn-sm" onclick={() => showTodoEditModal(todo)}>수정</button>
			<button class="btn btn-outline btn-sm">삭제</button>
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

<dialog id="todo-edit-modal" class="modal">
	<div class="modal-box">
		<form method="dialog">
			<button class="btn btn-circle btn-ghost btn-sm absolute right-2 top-2">✕</button>
		</form>
		<form class="grid gap-3" on:submit|preventDefault={submitEditTodoForm}>
			<label class="form-control">
				<div class="label">
					<span class="label-text">할일</span>
				</div>
				<input
					type="text"
					placeholder="할일을 입력해주세요."
					class="input input-bordered"
					bind:value={editingTodo.content}
				/>
			</label>
			<div class="grid grid-cols-2 gap-3">
				<button class="btn btn-primary">저장</button>
				<button
					type="button"
					class="btn btn-outline"
					onclick={() => {
					const modal = document.getElementById('todo-edit-modal') as HTMLDialogElement;
					modal.close();
				}}
					>취소</button
				>
			</div>
		</form>
	</div>

	<form method="dialog" class="modal-backdrop">
		<button></button>
	</form>
</dialog>

{@render TodoList(todos)}
<h3/>
{@render TodoList(todos)}