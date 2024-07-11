@import persons.Page
@param Page page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Persons</title>
</head>
<body>
${page.getStr()}
<ul>
    @for(var item : page.getPersons())
    <li>${item.getName()} ${item.getSurname()}</li>
    @endfor
</ul
</body>
</html> 