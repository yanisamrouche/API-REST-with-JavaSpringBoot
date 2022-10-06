<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/app" />
<c:url var="app" value="/app.js" />

<div id="myApp">

    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="${home}">Movies</a>
            <a class="navbar-brand" href="#" v-on:click="populateMovies()">Populate</a>
            <a class="navbar-brand" href="#" v-on:click="listMovies()">List of movies</a>
        </nav>
        <h1>My application</h1>
        <p>{{ message }}</p>
        <p>list = <span v-for="element in list">{{element}} - </span></p>
        <p>counter = {{counter}}</p>
        <button v-on:click="incCounter(1)">Plus un</button>
        <button v-on:click="incCounter(2)">Plus deux</button>
        <br/>
        <span v-on:mouseover="incCounter(1)">Il faut me survoler</span>
        <h1>Liste des films</h1>
        <table class="table">
            <tr>
                <th>Nom</th>
                <th>Annee</th>
                <th>Actions</th>
            </tr>
            <tr v-for="movie in movies">
                <td>{{movie.name}}</td>
                <td>{{movie.year}}</td>
                <td>
                    <button v-on:click="showMovie(movie.id)">Montrer</button>
                   
                </td>
                <td>
                    <button v-on:click="editMovie()">Editer</button>
                </td>
                <td>
                    <button v-on:click="removeMovie(movie.id)">Supprimer</button>
                </td>
            </tr>
        </table>

        <div v-if="(movie != null)">
            <h1>Film</h1>
            <p>nom : {{movie.name}}</p>
            <p>l'annee : {{movie.year}}</p>
            <p>la description : {{movie.description}}</p>
        </div>


        <form id="app" method="post" novalidate="true">

            <div class="form-group">
                <label>Name :</label>
                <input v-model="editable.name" class="form-control"
                       v-bind:class="{'is-invalid':errors.name}" />
                <div v-if="(errors.name)" class="alert alert-warning">
                    {{errors.name}}
                </div>
            </div>
            <div class="form-group">
                <label>Year :</label>
                <input v-model="editable.year" class="form-control"
                       v-bind:class="{'is-invalid':errors.year}" number />
                <div v-if="(errors.year)" class="alert alert-warning">
                    {{errors.year}}
                </div>
            </div>
            <div class="form-group">
                <label>Description :</label>
                <textarea v-model="editable.description" rows="5" cols="50"
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
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
