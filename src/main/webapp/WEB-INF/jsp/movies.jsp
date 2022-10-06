<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
	<h1>Liste des films</h1>

	<c:url var="movieAction" value="/movie" />
	<c:url var="deleteAction" value="/remove-movie" />

	<a class="btn btn-primary btn-sm"
	   href="/oldMovies">Montrer les films anciens</a>
	<a class="btn btn-primary btn-sm"
	   href="/create-movie">Creer un nouveau film</a>
	<table class="table">
		<tr>
			<th>Nom</th>
			<th>Annee</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="movie" items="${movies}">
			<tr>
				<td>${movie.name}</td>
				<td>${movie.year}</td>
				<td>
					<a class="btn btn-primary btn-sm" href="${movieAction}/${movie.id}">Montrer</a>
					<a class="btn btn-primary btn-sm" href="${deleteAction}/${movie.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
