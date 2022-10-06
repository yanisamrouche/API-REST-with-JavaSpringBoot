<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="movies" value="/movies" />

<div class="container">
    <h1>Veuillez confirmer la suppression</h1>

    <form:form method="post" modelAttribute="movie">

        <div class="form-group">
            <label>Nom :</label>
            <form:input path="name" cssClass="form-control"
                        cssErrorClass="form-control is-invalid" />
            <form:errors path="name" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <button  type="submit" class="btn btn-primary">OK</button>
            <a class="btn btn-primary" href="${movies}">Annuler</a>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
