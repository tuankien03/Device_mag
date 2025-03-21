import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, EventEmitter, inject, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CellAction } from '../../model/cellaction';
import { Pageable } from '../../model/pageable';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements AfterViewInit, OnChanges {
  @Input() pageable: Pageable = {pageNumber: 1, pageSize: 20 , property: '', direction: ''};
  @Input() displayedColumnsHeader: {[key: string]: string} = {};
  @Input() totalItems: number = 0;
  @Input() displayedColumns: string[] = [];
  @Input() dataSource: MatTableDataSource<any>;
  @Input() cellActions?: CellAction [] = [];
  @Input() hasAddButton: boolean = false;
  @Input() disableAction: (element: any, action: string) => boolean = () => false;

  @Output() pageableChange = new EventEmitter<Pageable>();
  @Output() onAction = new EventEmitter<{id: string, nameAction: string}>();
  @Output() search = new EventEmitter<string>();
  @Output() add = new EventEmitter<void>();
  @Output() refresh = new EventEmitter<void>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.pageableChange.emit(this.pageable)
    this.sort.sortChange.subscribe((sort: Sort) => {
      this.pageable.property = sort.active; 
      this.pageable.direction = sort.direction.toUpperCase(); 
      this.pageableChange.emit(this.pageable); 
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    this.dataSource.sort = this.sort;
    if (changes['totalItems']) {
        console.log("total change::", this.totalItems)
    }
  }

  onSearch(event: any) {
    const value = event.target.value.trim(); // Loại bỏ khoảng trắng đầu/cuối
  
    if (value.length === 0) {
      console.log("Search value is empty, ignoring...");
      return; // Không gửi event nếu giá trị rỗng
    }
  
    this.paginator.firstPage();
    this.search.emit(value);
  }

  onAdd() {
    this.add.emit();
  }

  onRefresh() {
    this.refresh.emit();
  }

  onActionClick(element: any, nameAction: string) {
    const idValue = element?.id || element?.deviceId || element?.userId || null;
    this.onAction.emit({id: idValue, nameAction});
  }

  get displayedColumnsWithoutLast(): string[] {
    return this.displayedColumns.slice(0, -1);
  }

  onPageChange(event: any) {
    console.log("Page change:", event);
    this.pageable.pageNumber = event.pageIndex + 1;
    this.pageable.pageSize = event.pageSize;
    this.pageableChange.emit(this.pageable);
  }



}
