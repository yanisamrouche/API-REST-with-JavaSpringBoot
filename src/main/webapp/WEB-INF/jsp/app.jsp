<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="app" value="/app.js" />

<div id="myApp">
	<div class="container">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="${home}">Movies</a> <a
				class="navbar-brand" href="#" v-on:click="populateMovies()">Populate</a>
			<a class="navbar-brand" href="#" v-on:click="listMovies()">List
				of movies</a>
		</nav>

		<div v-if="(movie == null) && (edit==null)">
			<h1>Liste des films</h1>
			<table class="table">
				<tr>
					<th>Nom</th>
					<th>Année</th>
					<th>Actions</th>
				</tr>
				<tr v-for="m in movies">
					<td>{{ m.name }}</td>
					<td>{{ m.year }}</td>
					<td><button v-on:click="showMovie(m.id)">Détails</button></td>
					<td><button v-on:click="removeMovie(m.id)">Supprimer</button></td>
					<td><button v-on:click="editMovie(m.id)">Éditer</button></td>
				</tr>
			</table>
		</div>

		<div v-if="(movie != null)">
			<h1>Movie information</h1>
			<div class="card">
				<div class="card-header">{{movie.name}}</div>
				<div class="card-body">
					<p class="card-text">Year: {{movie.year}}</p>
					<p class="card-text">{{movie.description}}</p>
					<button class="btn btn-primary" v-on:click="editMovie(movie.id)">Éditer</button>
				</div>
			</div>
		</div>

		<div v-if="(edit != null)">
			<h1>Modifier un film</h1>

			<form id="app" @submit="checkForm" method="post" novalidate="true">

				<div class="form-group">
					<label>Nom :</label> <input v-model="edit.name"
						v-bind:class="{'is-invalid':errors.name}" class="form-control" />
					<div v-if="(errors.name)" class="alert alert-warning">{{errors.name}}</div>
				</div>
				<div class="form-group">
					<label>Année :</label> <input v-model="edit.year"
						v-bind:class="{'is-invalid':errors.year}" class="form-control"
						number />
					<div v-if="(errors.year)" class="alert alert-warning">{{errors.year}}</div>
				</div>
				<div class="form-group">
					<label>Description :</label>
					<textarea v-model="edit.description" rows="10" cols="50"
						class="form-control"></textarea>
				</div>
				<div class="form-group">
					<button v-on:click.prevent="submitMovie()" class="btn btn-primary">
						Save</button>
					<button v-on:click="listMovies()" class="btn btn-primary">
						Abort</button>
				</div>
			</form>
		</div>

		<h1>My application</h1>
		<p>{{message}}</p>
		<p>counter = {{counter}}</p>
		<p>
			list = <span v-for="element in list">{{element}} - </span>
		</p>
		<button v-on:click="incCounter">Plus un</button>
		<span v-on:mouseover="incCounter">Il faut me survoler</span>
	</div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>

