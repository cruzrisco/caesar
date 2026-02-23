<g:textField id="CONTEXT" name="CONTEXT" value="${change?.affectedElement}" />
<g:textField id="POPULATION" name="POPULATION" value="${change?.affectedElement}" />
<g:select id="OPERATIONALIZATION" name="OPERATIONALIZATION"
    from="${caesar.OperationalizationElement}"
    value="${change?.affectedElement}"
/>
<g:select id="PROTOCOL" name="PROTOCOL"
    from="${caesar.ProtocolElement}"
    value="${change?.affectedElement}"
/>
<g:select id="EXPERIMENTER" name="EXPERIMENTER"
    from="${caesar.ExperimenterRole}"
    value="${change?.affectedElement}"
/>
<g:hiddenField name="affectedElement" value="${change?.affectedElement}" />

<script type="text/javascript" src="/assets/jquery-3.5.1.min.js?compile=false" ></script>
<script>
const ids = [
    'OPERATIONALIZATION',
    'PROTOCOL',
    'CONTEXT',
    'POPULATION',
    'EXPERIMENTER'
];

function ShowElementField( dimension, speed )
{
    for (let i = 0; i < ids.length; i++) {
        if( ids[i] == dimension ) {
            $( '#'+ids[i] ).show( speed );
        }
        else {
            $( '#'+ids[i] ).hide( speed );
        }
    }
    UpdateElementValue( dimension );
}

function UpdateElementValue( dimension )
{
    if ( dimension == 'CONTEXT' || dimension == 'POPULATION' ) {
        $('#affectedElement').val( $('#' + dimension).val() );
    }
    else {
        $('#affectedElement').val( $('#' + dimension + ' option:selected').val() );
    }
    console.log( 'UpdateElementValue(' + dimension + ')' );
    console.log( 'affectedElement.val() = ' + $('#affectedElement').val() );
    console.log( '$(#' + dimension + ').val() = ' + $('#' + dimension).val() );
    console.log( '$(#' + dimension + ' option:selected).val() = ' + $('#' + dimension + ' option:selected').val() );
}

$("#dimension").change( function() {
    const dimension = $("#dimension option:selected").val();
    ShowElementField( dimension, "slow" );
});

for (let i = 0; i < ids.length; i++) {
    $( '#'+ids[i] ).change( function() {
        const dimension = $("#dimension option:selected").val();
        UpdateElementValue( dimension );
    });
}

ShowElementField( $("#dimension option:selected").val(), 0 )
</script>
