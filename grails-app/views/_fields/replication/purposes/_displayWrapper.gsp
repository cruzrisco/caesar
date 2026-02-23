<ul>
<g:each in="${value}" var="purpose">
    <li>${message(code:purpose.getCodes().getAt(0))}</li>
</g:each>
</ul>
