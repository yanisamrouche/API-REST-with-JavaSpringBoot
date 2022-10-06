<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="home" value="/aaa" />
<c:url var="app" value="/app.js" />

<div id="myApp">
    <div class="container">
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

    </div>
</div>
<script src="${app}"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
