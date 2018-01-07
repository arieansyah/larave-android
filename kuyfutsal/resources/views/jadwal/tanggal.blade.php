<form class="form-inline">
    <div class="form-group">
        <select name="tanggal" class="form-control">
            @for($no = 1; $no <= 31; $no++)
                <option value="{{ $no }}" {{($no == $tanggal) ? 'selected' : null }}> {{ $no }}</option>
            @endfor
        </select>
    </div>
    <div class="form-group">
        <select name="bulan" class="form-control">

              @for($no = 1; $no <= 12; $no++)
                <option value="{{ $no }}" {{($no == $bulan) ?  'selected' : null}}> {{ $no }} </option>
            @endfor
        </select>
    </div>
    <div class="form-group">
        <select name="tahun" class="form-control">
            @for($no = $tahun; $no <= ($tahun + 1); $no++)
                <option value="{{ $no }}" {{($no == $tahun) ? 'selected' : null }}> {{ $no }}</option>
            @endfor
        </select>
    </div>
    <div class="form-group">
        <button class="btn btn-primary cari">Cari</button>
    </div>

</form>
